package team.gachi.watery.alarm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import team.gachi.watery.alarm.domain.AlarmLog;
import team.gachi.watery.alarm.event.AlarmLogSaveEvent;
import team.gachi.watery.alarm.repository.AlarmLogRepository;

@Service
@RequiredArgsConstructor
public class AlarmLogService {

    private final AlarmLogRepository alarmLogRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLog(AlarmLogSaveEvent event) {
        AlarmLog alarmLog = event.toDomain();
        alarmLogRepository.save(alarmLog);
    }
}