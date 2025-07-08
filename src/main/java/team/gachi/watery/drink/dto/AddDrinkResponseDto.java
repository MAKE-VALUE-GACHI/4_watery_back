package team.gachi.watery.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record AddDrinkResponseDto(
        @Schema(description = "음료 ID", example = "1")
        Long id
) {

    public static AddDrinkResponseDto of(Long id) {
        return AddDrinkResponseDto.builder()
                .id(id)
                .build();
    }
}
