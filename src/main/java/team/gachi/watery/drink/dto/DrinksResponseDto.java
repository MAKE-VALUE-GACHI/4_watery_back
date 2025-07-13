package team.gachi.watery.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record DrinksResponseDto(
        @Schema(description = "음료 목록")
        List<DrinkDto> drinks
) {
    public static DrinksResponseDto of(List<DrinkDto> drinks) {
        return DrinksResponseDto.builder()
                .drinks(drinks)
                .build();
    }
}
