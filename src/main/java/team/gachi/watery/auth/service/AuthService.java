package team.gachi.watery.auth.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.gachi.watery.auth.dto.SignInRequest;
import team.gachi.watery.auth.dto.SignInResponse;
import team.gachi.watery.oauth.OAuthClientResolver;
import team.gachi.watery.user.domain.Token;
import team.gachi.watery.user.domain.User;
import team.gachi.watery.user.repository.UserRepository;
import team.gachi.watery.util.JwtUtil;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final OAuthClientResolver oAuthClientResolver;

    @Transactional
    public SignInResponse signIn(String socialAccessToken, SignInRequest request) {
        User.Social social = oAuthClientResolver.getSocialData(request.socialType(), socialAccessToken);
        User signedUser = signIn(social, request);

        String refreshToken = jwtUtil.generateRefreshToken(signedUser.getId());
        String pushToken = request.pushToken();

        signedUser.getToken()
                .ifPresentOrElse(
                        token -> token.update(refreshToken, pushToken),
                        () -> {
                            Token newToken = Token.of(signedUser, refreshToken, pushToken);
                            signedUser.setToken(newToken);
                        }
                );

        String accessToken = jwtUtil.generateAccessToken(signedUser.getId());
        return SignInResponse.of(accessToken, signedUser);
    }

    private User signIn(User.Social social, SignInRequest request) {
        return userRepository.findBySocialTypeAndSocialId(social.socialType(), social.socialId())
                .orElseGet(() -> userRepository.save(request.toDomain(social.socialId())));
    }
}
