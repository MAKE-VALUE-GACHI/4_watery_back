package team.gachi.watery.drink.repository;


import team.gachi.watery.drink.domain.DrinkHistory;

import java.time.LocalDate;
import java.util.List;

public interface DrinkHistoryCustomRepository {
    int sumTotalDrinkAmountBy(Long drinkId, Long userId, LocalDate baseDate);

    int sumTotalHydrationAmount(Long userId, LocalDate baseDate);

    List<DrinkHistory> findByUserIdAndDateRange(Long userId, LocalDate startDate, LocalDate endDate);

    List<DrinkHistory> findRecentByUserIdAndDrinkId(Long userId, Long drinkId, int limit);

    List<DrinkHistory> findBy(Long userId, Long drinkId, LocalDate startDate, LocalDate endDate);
}
