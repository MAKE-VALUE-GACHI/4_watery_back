package team.gachi.watery.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import team.gachi.watery.common.StringListConverter;
import team.gachi.watery.drink.domain.ColorTemplate;
import team.gachi.watery.util.StringUtil;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ColorTemplateDto(
        @Schema(description = "색상 템플릿 ID", example = "1")
        Long colorTemplateId,
        @Schema(description = "색상 목록", example = "[\"#FF5733\", \"#33FF57\", \"#3357FF\"]")
        List<String> colors
) {
    public static ColorTemplateDto of(ColorTemplate colorTemplate) {
        return ColorTemplateDto.builder()
                .colorTemplateId(colorTemplate.getId())
                .colors(colorTemplate.getColors())
                .build();
    }
}
