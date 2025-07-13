package team.gachi.watery.drink.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.gachi.watery.drink.dto.AddDrinkRequestDto;
import team.gachi.watery.drink.dto.AddDrinkResponseDto;
import team.gachi.watery.drink.dto.DrinksResponseDto;
import team.gachi.watery.drink.dto.HydrationAmountResponseDto;
import team.gachi.watery.drink.service.DrinkService;
import team.gachi.watery.dto.WateryResponse;

import java.security.Principal;
import java.time.LocalDate;

@Tag(name = "음료", description = "음료 관련 API")
@RequiredArgsConstructor
@RestController("/api/v1/drinks")
public class DrinkController {
    private final DrinkService drinkService;

    @Operation(summary = "음료 추가", description = "새로운 음료를 추가합니다.")
    @PostMapping
    public WateryResponse<AddDrinkResponseDto> addDrink(
            @Parameter(hidden = true)
            Principal principal,

            @Valid
            @RequestBody
            AddDrinkRequestDto request
    ) {
        AddDrinkResponseDto response = drinkService.addDrink(Long.valueOf(principal.getName()), request);

        return WateryResponse.of(response);
    }

    @Operation(summary = "음료 목록 조회", description = "사용자의 음료 목록을 조회합니다.")
    @GetMapping
    public WateryResponse<DrinksResponseDto> getDrinks(
            @Parameter(hidden = true)
            Principal principal,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "기준 날짜 (없으면 당일 기준)", example = "2025-07-01")
            LocalDate baseDate
    ) {
        DrinksResponseDto response = drinkService.getDrinks(Long.valueOf(principal.getName()), baseDate);

        return WateryResponse.of(response);
    }

    @Operation(
            summary = "일일 수분 섭취량 및 목표 수분량 조회",
            description = "사용자의 일일 수분 섭취량과 일일 목표 수분 섭취량을 조회합니다."
    )
    @GetMapping("/amount")
    public WateryResponse<HydrationAmountResponseDto> getHydrationAmount(
            @Parameter(hidden = true)
            Principal principal,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "기준 날짜 (없으면 당일 기준)", example = "2025-07-01")
            LocalDate baseDate
    ) {
        HydrationAmountResponseDto response = drinkService.getHydrationAmount(Long.valueOf(principal.getName()), baseDate);

        return WateryResponse.of(response);
    }
}
