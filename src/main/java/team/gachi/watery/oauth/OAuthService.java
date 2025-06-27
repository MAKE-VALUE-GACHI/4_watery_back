package team.gachi.watery.oauth;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import team.gachi.watery.domain.User;
import team.gachi.watery.exception.ExceptionCode;
import team.gachi.watery.exception.WateryException;
import team.gachi.watery.oauth.google.GoogleOAuthService;
import team.gachi.watery.oauth.kakao.KakaoOAuthService;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final KakaoOAuthService kakaoOAuthService;
    private final GoogleOAuthService googleOAuthService;

    public User.Social login(User.SocialType socialType, String accessToken) {
        val socialId = getSocialId(socialType, accessToken);
        return new User.Social(socialType, socialId);
    }

    private String getSocialId(User.SocialType socialType, String socialAccessToken) {
        return switch (socialType) {
            case GOOGLE -> googleOAuthService.getGoogleData(socialAccessToken);
            case KAKAO -> kakaoOAuthService.getKakaoData(socialAccessToken);
            case NONE -> throw new WateryException(ExceptionCode.INVALID_ENUM_TYPE);
        };
    }
}
