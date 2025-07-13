package team.gachi.watery.drink.dto;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ColorTemplatesResponseDto(
        List<ColorTemplateDto> colorTemplates
) {
    public static ColorTemplatesResponseDto of(List<ColorTemplateDto> colorTemplates) {
        return ColorTemplatesResponseDto.builder()
                .colorTemplates(colorTemplates)
                .build();
    }
}
