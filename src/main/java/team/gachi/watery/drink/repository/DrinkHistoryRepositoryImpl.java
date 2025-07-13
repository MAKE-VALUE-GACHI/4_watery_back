package team.gachi.watery.drink.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.gachi.watery.drink.domain.DrinkHistory;
import team.gachi.watery.drink.domain.QDrink;
import team.gachi.watery.drink.domain.QDrinkHistory;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class DrinkHistoryRepositoryImpl implements DrinkHistoryCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public int sumTotalDrinkAmountBy(Long drinkId, Long userId, LocalDate baseDate) {
        QDrinkHistory drinkHistory = QDrinkHistory.drinkHistory;

        Integer totalAmount = queryFactory
                .select(drinkHistory.amount.sum())
                .from(drinkHistory)
                .where(
                        drinkHistory.drink.id.eq(drinkId),
                        drinkHistory.user.id.eq(userId),
                        drinkHistory.drinkAt.between(baseDate.atStartOfDay(), baseDate.atTime(23, 59, 59))
                )
                .fetchOne();

        return totalAmount != null ? totalAmount : 0;
    }

    @Override
    public int sumTotalHydrationAmount(Long userId, LocalDate baseDate) {
        QDrinkHistory drinkHistory = QDrinkHistory.drinkHistory;
        QDrink drink = QDrink.drink;

        Integer totalAmount = queryFactory
                .select(drinkHistory.amount.sum())
                .from(drinkHistory)
                .innerJoin(drink)
                .on(drink.id.eq(drinkHistory.drink.id)
                        .and(drink.includesDailyHydrationGoal))
                .where(
                        drinkHistory.user.id.eq(userId),
                        drinkHistory.drinkAt.between(baseDate.atStartOfDay(), baseDate.atTime(23, 59, 59))
                )
                .fetchOne();

        return totalAmount != null ? totalAmount : 0;
    }
}
