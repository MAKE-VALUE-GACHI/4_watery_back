package team.gachi.watery.drink.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.gachi.watery.drink.domain.DrinkHistory;
import team.gachi.watery.drink.domain.QDrink;
import team.gachi.watery.drink.domain.QDrinkHistory;

import java.time.LocalDate;
import java.util.List;

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
                        drinkHistory.drinkAt.goe(baseDate.atStartOfDay()),
                        drinkHistory.drinkAt.lt(baseDate.plusDays(1).atStartOfDay())
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
                        drinkHistory.drinkAt.goe(baseDate.atStartOfDay()),
                        drinkHistory.drinkAt.lt(baseDate.plusDays(1).atStartOfDay())
                )
                .fetchOne();

        return totalAmount != null ? totalAmount : 0;
    }

    public List<DrinkHistory> findByUserIdAndDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        QDrinkHistory drinkHistory = QDrinkHistory.drinkHistory;
        QDrink drink = QDrink.drink;
        return queryFactory
                .selectFrom(drinkHistory)
                .innerJoin(drink)
                .on(drink.id.eq(drinkHistory.drink.id)
                        .and(drink.includesDailyHydrationGoal))
                .where(
                        drinkHistory.user.id.eq(userId),
                        drinkHistory.drinkAt.goe(startDate.atStartOfDay()),
                        drinkHistory.drinkAt.lt(endDate.plusDays(1).atStartOfDay())
                )
                .fetch();
    }

    public List<DrinkHistory> findBy(Long userId, Long drinkId, LocalDate startDate, LocalDate endDate) {
        QDrinkHistory drinkHistory = QDrinkHistory.drinkHistory;
        return queryFactory
                .selectFrom(drinkHistory)
                .where(
                        drinkHistory.user.id.eq(userId),
                        drinkId != null ? drinkHistory.drink.id.eq(drinkId) : null,
                        drinkHistory.drinkAt.goe(startDate.atStartOfDay()),
                        drinkHistory.drinkAt.lt(endDate.plusDays(1).atStartOfDay())
                )
                .fetch();
    }
}
