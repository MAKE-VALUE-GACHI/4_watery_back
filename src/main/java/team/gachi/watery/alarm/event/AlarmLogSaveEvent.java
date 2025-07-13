package team.gachi.watery.alarm.event;

import lombok.Builder;
import team.gachi.watery.alarm.domain.Alarm;
import team.gachi.watery.alarm.domain.AlarmLog;
import team.gachi.watery.user.domain.User;

import java.time.LocalDateTime;

@Builder
public record AlarmLogSaveEvent(
        User user,
        Alarm alarm,
        String title,
        String body,
        LocalDateTime sentAt,
        String errorMessage,
        String failureReason) {

    public AlarmLog toDomain(){
        return AlarmLog.of(
                user,
                alarm,
                title,
                body,
                sentAt,
                errorMessage,
                failureReason
        );
    }
}
