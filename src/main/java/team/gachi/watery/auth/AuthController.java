package team.gachi.watery.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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

    @Operation(
            summary = "소셜 로그인",
            description = "소셜서비스 Access Token을 Authorization 헤더에 담아 로그인합니다."
    )
    @PostMapping("/sign-in")
    public WateryResponse<SignInResponse> signIn(
            @Parameter(hidden = true)
            @RequestHeader("Authorization") String socialAccessToken,
            @RequestBody SignInRequest request
    ) {
        return WateryResponse.of(authService.signIn(socialAccessToken, request), "로그인 성공");
    }
}
