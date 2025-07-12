package team.gachi.watery.drink.repository;


import java.time.LocalDate;

public interface DrinkHistoryCustomRepository {
    int sumTotalDrinkAmountBy(Long drinkId, Long userId, LocalDate startDate, LocalDate endDate);
}
