package team.gachi.watery.alarm.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.gachi.watery.alarm.domain.Alarm;
import team.gachi.watery.alarm.event.AlarmLogSaveEvent;
import team.gachi.watery.alarm.repository.AlarmRepository;
import team.gachi.watery.user.domain.Token;
import team.gachi.watery.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Scheduled(cron = "0 0 9 * * *", zone = "Asia/Seoul")
    @Transactional
    public void sendDailyReminder() {
        List<Alarm> alarms = alarmRepository.findAllByAlarmEnabled();

        for (Alarm alarm : alarms) {
            String title = "물마셔요";
            String body = "몸도 마음도 촉촉하게! 물 한 잔 잊지마세요";

            sendPushMessage(alarm, title, body);
        }
    }

    private void sendPushMessage(Alarm alarm, String title, String body) {
        User user = alarm.getUser();
        Token token = user.getToken();

        String targetToken = token != null ? token.getPushToken() : null;
        if (targetToken == null) {
            log.info("푸시 토큰 없음: {}", user.getId());
            return;
        }

        Message message = Message.builder()
                .setToken(targetToken)
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .build();

        String errorMessage = null;
        String failureReason = null;
        LocalDateTime sentAt = LocalDateTime.now();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("푸시 전송 성공: {}", response);
        } catch (FirebaseMessagingException e) {
            errorMessage = e.getMessage();
            failureReason = e.getMessagingErrorCode() != null ? e.getMessagingErrorCode().name() : null;
            log.error("푸시 전송 실패: {}", errorMessage);
        } finally {
            eventPublisher.publishEvent(
                    AlarmLogSaveEvent.builder()
                            .user(user)
                            .alarm(alarm)
                            .body(body)
                            .title(title)
                            .sentAt(sentAt)
                            .errorMessage(errorMessage)
                            .failureReason(failureReason)
                            .build()
            );
        }
    }
}
