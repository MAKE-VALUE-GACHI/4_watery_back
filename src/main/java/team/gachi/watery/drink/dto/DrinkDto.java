package team.gachi.watery.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import team.gachi.watery.drink.domain.Drink;

@Builder(access = AccessLevel.PRIVATE)
public record DrinkDto(
        @Schema(description = "음료 ID", example = "1")
        Long id,
        @Schema(description = "음료 이름", example = "물")
        String name,
        @Schema(description = "총 섭취량", example = "500")
        int totalAmount,
        @Schema(description = "일일 수분 섭취 목표 포함 여부", example = "true")
        boolean includesDailyHydrationGoal,
        @Schema(description = "색상 템플릿 정보")
        ColorTemplateDto colorTemplate
) {
    public static DrinkDto of(Drink drink, int totalAmount) {
        return DrinkDto.builder()
                .id(drink.getId())
                .name(drink.getName())
                .totalAmount(totalAmount)
                .includesDailyHydrationGoal(drink.getIncludesDailyHydrationGoal())
                .colorTemplate(ColorTemplateDto.of(drink.getColorTemplate()))
                .build();
    }
}
