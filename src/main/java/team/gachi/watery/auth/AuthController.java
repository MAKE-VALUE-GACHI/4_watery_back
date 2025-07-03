package team.gachi.watery.auth;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.gachi.watery.auth.dto.SignInRequest;
import team.gachi.watery.auth.dto.SignInResponse;
import team.gachi.watery.dto.WateryResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-in")
    public WateryResponse<SignInResponse> signIn(
            @Parameter(description = "소셜 로그인 액세스 토큰", example = "Bearer kakao-access-token")
            @RequestHeader("Authorization") String socialAccessToken,
            @RequestBody SignInRequest request
    ) {
        return WateryResponse.of(authService.signIn(socialAccessToken, request), "로그인 성공");
    }
}
