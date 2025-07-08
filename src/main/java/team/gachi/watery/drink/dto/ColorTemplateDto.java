package team.gachi.watery.drink.dto;

import lombok.AccessLevel;
import lombok.Builder;
import team.gachi.watery.drink.domain.ColorTemplate;
import team.gachi.watery.util.StringUtil;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ColorTemplateDto(
        Long colorTemplateId,
        List<String> colors
) {
    public static ColorTemplateDto of(ColorTemplate colorTemplate) {
        return ColorTemplateDto.builder()
                .colorTemplateId(colorTemplate.getId())
                .colors(StringUtil.parseToList(colorTemplate.getColors()))
                .build();
    }
}
