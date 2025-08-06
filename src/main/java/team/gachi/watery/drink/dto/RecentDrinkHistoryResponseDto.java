package team.gachi.watery.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "최근 음료 기록 응답")
public record RecentDrinkHistoryResponseDto(
    @Schema(description = "음료량 리스트 (최신순)", example = "[500, 300, 250]")
    List<Integer> amounts
) {
}
