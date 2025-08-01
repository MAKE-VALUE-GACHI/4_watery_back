package team.gachi.watery.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateDrinkRequestDto(
        @NotBlank
        @Size(max = 10)
        @Schema(description = "음료 이름", example = "커피")
        String name,

        @NotNull
        @Schema(description = "색상 템플릿 ID", example = "1")
        Long colorTemplateId,

        @NotNull
        @Schema(description = "일일 수분 섭취 목표 포함 여부", example = "true")
        Boolean includesDailyHydrationGoal
) {
}
