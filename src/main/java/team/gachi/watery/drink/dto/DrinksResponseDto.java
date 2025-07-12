package team.gachi.watery.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record DrinksResponseDto(
        @Schema(description = "일일 수분 섭취 목표", example = "2000")
        int dailyHydrationGoal,
        @Schema(description = "총 수분 섭취량", example = "1500")
        int totalHydrationAmount,
        @Schema(description = "음료 목록")
        List<DrinkDto> drinks
) {
    public static DrinksResponseDto of(int dailyHydrationGoal, int totalHydrationAmount, List<DrinkDto> drinks) {
        return DrinksResponseDto.builder()
                .dailyHydrationGoal(dailyHydrationGoal)
                .totalHydrationAmount(totalHydrationAmount)
                .drinks(drinks)
                .build();
    }
}
