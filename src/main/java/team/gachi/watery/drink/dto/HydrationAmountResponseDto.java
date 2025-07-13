package team.gachi.watery.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record HydrationAmountResponseDto(
        @Schema(description = "일일 수분 섭취 목표", example = "2000")
        int dailyHydrationGoal,
        @Schema(description = "총 섭취량", example = "500")
        int totalHydrationAmount
) {
    public static HydrationAmountResponseDto of(int dailyHydrationGoal, int totalHydrationAmount) {
        return HydrationAmountResponseDto.builder()
                .dailyHydrationGoal(dailyHydrationGoal)
                .totalHydrationAmount(totalHydrationAmount)
                .build();
    }
}

