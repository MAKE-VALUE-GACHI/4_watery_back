package team.gachi.watery.auth.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import team.gachi.watery.user.User;

public record SignInRequest(
        @JsonProperty("social")
        @Schema(description = "소셜 로그인 제공자", example = "KAKAO")
        User.SocialType socialType,

        @Schema(description = "알림을 위한 토큰")
        String fcmToken
) {
    public User toDomain(String socialId) {
        return User.of(socialType, socialId);
    }
}
