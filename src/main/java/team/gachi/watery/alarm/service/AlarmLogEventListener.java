package team.gachi.watery.alarm.service;


import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import team.gachi.watery.alarm.event.AlarmLogSaveEvent;

@Component
@RequiredArgsConstructor
public class AlarmLogEventListener {

    private final AlarmLogService alarmLogService;

    @Async
    @EventListener
    public void handle(AlarmLogSaveEvent event) {
        alarmLogService.saveLog(event);
    }
}
