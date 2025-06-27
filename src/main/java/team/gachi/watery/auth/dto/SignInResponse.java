package team.gachi.watery.auth.dto;

import lombok.AccessLevel;
import lombok.Builder;
import team.gachi.watery.domain.User;

@Builder(access = AccessLevel.PRIVATE)
public record SignInResponse(
        String accessToken,
        String refreshToken
) {

    public static SignInResponse of(String accessToken, User user) {
        return SignInResponse.builder()
                .accessToken(accessToken)
                .refreshToken(user.getRefreshToken())
                .build();
    }
}
