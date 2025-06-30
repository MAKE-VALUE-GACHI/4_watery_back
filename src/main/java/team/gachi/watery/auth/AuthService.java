package team.gachi.watery.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.gachi.watery.auth.dto.SignInRequest;
import team.gachi.watery.auth.dto.SignInResponse;
import team.gachi.watery.domain.User;
import team.gachi.watery.oauth.OAuthClientResolver;
import team.gachi.watery.user.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final TokenGenerator tokenGenerator;
    private final UserRepository userRepository;
    private final OAuthClientResolver oAuthClientResolver;

    @Transactional
    public SignInResponse signIn(String socialAccessToken, SignInRequest request) {
        User.Social social = oAuthClientResolver.login(request.socialType(), socialAccessToken);
        User signedUser = signIn(social, request);
        signedUser.updateTokenInLogin(
                tokenGenerator.generateRefreshToken(signedUser.getId()),
                request.fcmToken());

        return SignInResponse.of(tokenGenerator.generateAccessToken(signedUser.getId()), signedUser);
    }

    private User signIn(User.Social social, SignInRequest request) {
        return userRepository.findBySocialTypeAndSocialId(social.socialType(), social.socialId())
                .orElseGet(() -> userRepository.save(request.toDomain(social.socialId())));
    }
}
