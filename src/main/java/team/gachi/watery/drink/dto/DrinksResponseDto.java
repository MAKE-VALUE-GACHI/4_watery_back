package team.gachi.watery.drink.dto;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record DrinksResponseDto(
        int dailyHydrationGoal,
        List<DrinkDto> drinks
) {
    public static DrinksResponseDto of(int dailyHydrationGoal, List<DrinkDto> drinks) {
        return DrinksResponseDto.builder()
                .dailyHydrationGoal(dailyHydrationGoal)
                .drinks(drinks)
                .build();
    }
}
