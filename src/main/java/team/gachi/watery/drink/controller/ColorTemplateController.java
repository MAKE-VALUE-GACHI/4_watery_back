package team.gachi.watery.drink.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.gachi.watery.drink.dto.ColorTemplatesResponseDto;
import team.gachi.watery.drink.service.ColorTemplateService;
import team.gachi.watery.dto.WateryResponse;

@Tag(name = "색상 템플릿", description = "색상 템플릿 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/color-templates")
public class ColorTemplateController {
    private final ColorTemplateService colorTemplateService;

    @Operation(summary = "색상 템플릿 목록 조회", description = "색상 템플릿 목록을 조회합니다.")
    @GetMapping
    public WateryResponse<ColorTemplatesResponseDto> getColorTemplate() {
        ColorTemplatesResponseDto response = colorTemplateService.getColorTemplate();

        return WateryResponse.of(response);
    }

}
