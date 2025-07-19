package team.gachi.watery.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import team.gachi.watery.drink.domain.DrinkHistory;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record DrinkHistoryDto(
        @Schema(description = "음료 섭취 기록 ID", example = "1")
        Long id,

        @Schema(description = "음료 ID", example = "1")
        Long drinkId,

        @Schema(description = "음료 이름", example = "물")
        String drinkName,

        @Schema(description = "음료 섭취량", example = "500")
        int amount,

        @Schema(description = "음료 섭취 시간", example = "2024-07-16T10:00:00")
        LocalDateTime drinkAt
) {

    public static DrinkHistoryDto of(DrinkHistory drinkHistory) {
        return DrinkHistoryDto.builder()
                .id(drinkHistory.getId())
                .drinkId(drinkHistory.getDrink().getId())
                .drinkName(drinkHistory.getDrink().getName())
                .amount(drinkHistory.getAmount())
                .drinkAt(drinkHistory.getDrinkAt())
                .build();
    }
}
