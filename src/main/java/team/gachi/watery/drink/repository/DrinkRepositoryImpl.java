package team.gachi.watery.drink.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.gachi.watery.common.Status;
import team.gachi.watery.drink.domain.Drink;
import team.gachi.watery.drink.domain.QDrink;
import team.gachi.watery.drink.domain.QDrinkHistory;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DrinkRepositoryImpl implements DrinkCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Drink> findAllBy(Long userId, Status status) {
        QDrink drink = QDrink.drink;

        return queryFactory
                .select(drink)
                .from(drink)
                .where(
                        drink.user.id.eq(userId),
                        drink.status.eq(status)
                )
                .fetch();
    }
}
