package team.gachi.watery.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddDrinkRequestDto(
        @Schema(description = "음료 템플릿 ID (템플릿을 사용할 경우 필수, 미사용 시 null)", example = "1")
        Long drinkTemplateId,

        @Size(max = 10)
        @Schema(description = "음료 이름 (템플릿을 사용하지 않을 경우 필수)", example = "커피")
        String name,

        @Schema(description = "색상 템플릿 ID (템플릿을 사용하지 않을 경우 필수)", example = "1")
        Long colorTemplateId,

        @NotNull
        @Schema(description = "일일 수분 섭취 목표 포함 여부", example = "true")
        Boolean includesDailyHydrationGoal
) {
}
