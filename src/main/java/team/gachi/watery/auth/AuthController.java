package team.gachi.watery.auth;

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

    @RequestMapping("/sign-in")
    @PostMapping
    public WateryResponse<SignInResponse> signIn(
            @RequestHeader("Authorization") String socialAccessToken,
            @RequestBody SignInRequest request
    ) {
        return WateryResponse.of(authService.signIn(socialAccessToken, request), "로그인 성공");
    }
}
