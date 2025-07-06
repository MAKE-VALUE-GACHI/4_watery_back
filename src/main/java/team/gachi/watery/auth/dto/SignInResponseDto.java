package team.gachi.watery.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import team.gachi.watery.user.domain.User;

@Builder(access = AccessLevel.PRIVATE)
public record SignInResponseDto(
        @Schema(description = "WATERY JWT 액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String accessToken,

        @Schema(description = "WATERY JWT 리프레시 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String refreshToken
) {

    public static SignInResponseDto of(String accessToken, User user) {
        return SignInResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(user.getRefreshToken())
                .build();
    }
}
