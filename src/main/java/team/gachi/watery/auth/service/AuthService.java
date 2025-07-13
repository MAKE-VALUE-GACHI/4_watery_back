package team.gachi.watery.auth.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.gachi.watery.alarm.domain.Alarm;
import team.gachi.watery.auth.dto.SignInRequestDto;
import team.gachi.watery.auth.dto.SignInResponseDto;
import team.gachi.watery.oauth.OAuthClientResolver;
import team.gachi.watery.user.domain.Token;
import team.gachi.watery.user.domain.User;
import team.gachi.watery.user.repository.UserRepository;
import team.gachi.watery.util.JwtUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final OAuthClientResolver oAuthClientResolver;

    @Transactional
    public SignInResponseDto signIn(String socialAccessToken, SignInRequestDto request) {
        User.Social social = oAuthClientResolver.getSocialData(request.socialType(), socialAccessToken);
        User signedUser = signIn(social, request);

        String refreshToken = jwtUtil.generateRefreshToken(signedUser.getId());
        String pushToken = request.pushToken();

        Token token = signedUser.getToken();

        if (token != null) {
            token.update(refreshToken, pushToken);
        } else {
            Token newToken = Token.of(signedUser, refreshToken, pushToken);
            Alarm alarm = Alarm.of(signedUser);

            signedUser.setToken(newToken);
            signedUser.setAlarms(List.of(alarm));
        }

        String accessToken = jwtUtil.generateAccessToken(signedUser.getId());
        return SignInResponseDto.of(accessToken, signedUser);
    }

    private User signIn(User.Social social, SignInRequestDto request) {
        return userRepository.findBySocialTypeAndSocialId(social.socialType(), social.socialId())
                .orElseGet(() -> userRepository.save(request.toDomain(social.socialId())));
    }
}
