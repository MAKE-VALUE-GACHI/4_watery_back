package team.gachi.watery.auth.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import team.gachi.watery.domain.User;

public record SignInRequest(
        @JsonProperty("social")
        User.SocialType socialType,

        String fcmToken
) {
    public User toDomain(String socialId) {
        return User.of(socialType, socialId, fcmToken);
    }
}
