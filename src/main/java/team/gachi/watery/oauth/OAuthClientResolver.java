package team.gachi.watery.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.gachi.watery.exception.ExceptionCode;
import team.gachi.watery.exception.WateryException;
import team.gachi.watery.oauth.google.GoogleOAuthClient;
import team.gachi.watery.oauth.kakao.KakaoOAuthClient;
import team.gachi.watery.user.domain.User;

@Component
@RequiredArgsConstructor
public class OAuthClientResolver {

    private final KakaoOAuthClient kakaoOAuthClient;
    private final GoogleOAuthClient googleOAuthClient;

    public User.Social getSocialData(User.SocialType socialType, String accessToken) {
        String socialId = getSocialId(socialType, accessToken);
        return new User.Social(socialType, socialId);
    }

    private String getSocialId(User.SocialType socialType, String socialAccessToken) {
        return switch (socialType) {
            case GOOGLE -> googleOAuthClient.getGoogleData(socialAccessToken);
            case KAKAO -> kakaoOAuthClient.getKakaoData(socialAccessToken);
            case NONE -> throw new WateryException(ExceptionCode.INVALID_ENUM_TYPE);
        };
    }
}
