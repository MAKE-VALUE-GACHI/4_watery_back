package team.gachi.watery.drink.dto;

import java.util.List;

public record DailyDrinkHistoriesResponseDto(
    List<DrinkHistoryDto> drinkHistories
) {
}
