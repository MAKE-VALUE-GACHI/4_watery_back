package team.gachi.watery.alarm.repository;

import team.gachi.watery.alarm.domain.Alarm;

import java.util.List;

public interface AlarmCustomRepository {

    List<Alarm> findAllByAlarmEnabled();
}
