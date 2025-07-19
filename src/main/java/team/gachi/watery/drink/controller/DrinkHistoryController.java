package team.gachi.watery.drink.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import team.gachi.watery.drink.dto.AddDrinkHistoryRequestDto;
import team.gachi.watery.drink.dto.DailyDrinkHistoriesResponseDto;
import team.gachi.watery.drink.dto.WeeklyReportResponseDto;
import team.gachi.watery.drink.service.DrinkHistoryService;
import team.gachi.watery.dto.WateryResponse;

import java.security.Principal;
import java.time.LocalDate;

@Tag(name = "음료 섭취 기록", description = "음료 섭취 기록 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/drink-histories")
public class DrinkHistoryController {
    private final DrinkHistoryService drinkHistoryService;

    @Operation(summary = "음료 섭취 기록 추가", description = "음료 섭취 기록을 추가합니다.")
    @PostMapping
    public WateryResponse<?> addDrinkHistory(
            @Parameter(hidden = true)
            Principal principal,

            @Valid @RequestBody
            AddDrinkHistoryRequestDto request
    ) {
        drinkHistoryService.addDrinkHistory(Long.valueOf(principal.getName()), request);

        return WateryResponse.of("ok");
    }

    @Operation(summary = "일일 음료 섭취 기록 조회", description = "일일 음료 섭취 기록을 조회합니다.")
    @GetMapping("/daily")
    public WateryResponse<DailyDrinkHistoriesResponseDto> getDailyDrinkHistories(
            @Parameter(hidden = true)
            Principal principal,

            @Parameter(description = "음료 ID (음료 ID가 없으면 전체 음료 섭취 기록 조회)", example = "1")
            @RequestParam("drinkId")
            Long drinkId,

            @Parameter(description = "기준 날짜", example = "2024-07-16")
            @RequestParam("baseDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate baseDate
    ) {
        DailyDrinkHistoriesResponseDto result =
                drinkHistoryService.getDailyDrinkHistories(Long.valueOf(principal.getName()), drinkId, baseDate);
        return WateryResponse.of(result);
    }

    @Operation(summary = "음료 섭취 기록 1주일 조회", description = "음료 섭취 기록을 조회합니다.")
    @GetMapping
    public WateryResponse<WeeklyReportResponseDto> getDrinkHistories(
            @Parameter(hidden = true)
            Principal principal,

            @Parameter(
                    description = "기준 날짜 (해당 날짜를 포함해 이전 6일까지 총 7일 조회)",
                    example = "2024-07-16"
            )
            @RequestParam("baseDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate baseDate
    ) {
        WeeklyReportResponseDto result = drinkHistoryService.getDrinkHistory(Long.valueOf(principal.getName()), baseDate);
        return WateryResponse.of(result);
    }


    @Operation(summary = "음료 섭취 기록 삭제", description = "음료 섭취 기록을 삭제합니다.")
    @DeleteMapping("/{drinkHistoryId}")
    public WateryResponse<?> addDrinkHistory(
            @Parameter(hidden = true)
            Principal principal,

            @Schema(description = "음료 기록 ID", example = "1")
            @PathVariable Long drinkHistoryId
    ) {
        drinkHistoryService.deleteDrinkHistory(Long.valueOf(principal.getName()), drinkHistoryId);

        return WateryResponse.of("ok");
    }
}
