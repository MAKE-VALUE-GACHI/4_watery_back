package team.gachi.watery.drink.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record AddDrinkResponse(
        @Schema(description = "음료 ID", example = "1")
        Long id
) {

    public static AddDrinkResponse of(Long id) {
        return AddDrinkResponse.builder()
                .id(id)
                .build();
    }
}
