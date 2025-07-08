package team.gachi.watery.drink.dto;

import lombok.AccessLevel;
import lombok.Builder;
import team.gachi.watery.drink.domain.Drink;

@Builder(access = AccessLevel.PRIVATE)
public record DrinkDto(
        Long id,
        String name,
        boolean includesDailyHydrationGoal,
        ColorTemplateDto colorTemplate
) {
    public static DrinkDto of(Drink drink) {
        return DrinkDto.builder()
                .id(drink.getId())
                .name(drink.getName())
                .includesDailyHydrationGoal(drink.getIncludesDailyHydrationGoal())
                .colorTemplate(ColorTemplateDto.of(drink.getColorTemplate()))
                .build();
    }
}
