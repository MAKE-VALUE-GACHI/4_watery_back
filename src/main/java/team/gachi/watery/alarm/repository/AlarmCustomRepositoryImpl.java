package team.gachi.watery.alarm.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.gachi.watery.alarm.domain.Alarm;

import java.util.List;

import static team.gachi.watery.alarm.domain.QAlarm.alarm;

@Repository
@RequiredArgsConstructor
public class AlarmCustomRepositoryImpl implements AlarmCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Alarm> findAllByAlarmEnabled() {
        return jpaQueryFactory.selectFrom(alarm)
                .where(
                        alarm.enabled.eq(true)
                )
                .fetch();
    }
}
