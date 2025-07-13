package team.gachi.watery.alarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.gachi.watery.alarm.domain.AlarmLog;

public interface AlarmLogRepository extends JpaRepository<AlarmLog, Long> {
}
