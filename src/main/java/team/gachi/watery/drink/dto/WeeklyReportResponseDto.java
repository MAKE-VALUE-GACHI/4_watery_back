package team.gachi.watery.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "주간 음료 섭취 리포트 응답 DTO")
public record WeeklyReportResponseDto(

        @Schema(description = "조회 기간 정보 (시작일 ~ 종료일)")
        DateRange range,

        @Schema(description = "일별 섭취 기록 리스트")
        List<DailyConsumption> dailyReports
) {

    @Schema(description = "날짜 범위")
    public record DateRange(
            @Schema(description = "조회 시작 날짜", example = "2024-07-10")
            LocalDate startDate,

            @Schema(description = "조회 종료 날짜", example = "2024-07-16")
            LocalDate endDate
    ) {}

    @Schema(description = "일별 섭취 정보")
    public record DailyConsumption(
            @Schema(description = "날짜", example = "2024-07-15")
            LocalDate date,

            @Schema(description = "섭취량 (ml)", example = "1200")
            int amount
    ) {}
}