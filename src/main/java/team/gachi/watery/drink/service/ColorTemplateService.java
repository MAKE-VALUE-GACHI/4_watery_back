package team.gachi.watery.drink.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.gachi.watery.drink.domain.ColorTemplate;
import team.gachi.watery.drink.dto.ColorTemplateDto;
import team.gachi.watery.drink.dto.ColorTemplatesResponseDto;
import team.gachi.watery.drink.repository.ColorTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ColorTemplateService {
    private final ColorTemplateRepository colorTemplateRepository;

    public ColorTemplatesResponseDto getColorTemplate() {
        List<ColorTemplate> colorTemplates = colorTemplateRepository.findAll();

        List<ColorTemplateDto> colorTemplateDtos = colorTemplates.stream()
                .map(ColorTemplateDto::of)
                .toList();

        return ColorTemplatesResponseDto.of(colorTemplateDtos);
    }
}
