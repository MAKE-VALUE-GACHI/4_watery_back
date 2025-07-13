package team.gachi.watery.alarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.gachi.watery.alarm.domain.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Long>, AlarmCustomRepository {
}
