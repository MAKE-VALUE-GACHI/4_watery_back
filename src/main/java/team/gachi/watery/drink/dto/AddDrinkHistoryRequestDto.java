package team.gachi.watery.drink.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record AddDrinkHistoryRequestDto(
        @NotNull
        @Schema(description = "음료 ID", example = "1")
        Long drinkId,

        @NotNull
        @Positive
        @Schema(description = "섭취량", example = "500")
        Integer amount,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @Schema(description = "섭취 시간 (미입력 시 등록 시간 기준)", example = "2025-07-01T12:00:00", format = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime drinkAt
) {
}
