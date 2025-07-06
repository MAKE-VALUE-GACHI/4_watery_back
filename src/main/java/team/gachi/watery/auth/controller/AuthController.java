package team.gachi.watery.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.gachi.watery.auth.service.AuthService;
import team.gachi.watery.auth.dto.SignInRequestDto;
import team.gachi.watery.auth.dto.SignInResponseDto;
import team.gachi.watery.dto.WateryResponse;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "소셜 로그인",
            description = "소셜서비스 Access Token을 Authorization 헤더에 담아 로그인합니다."
    )
    @PostMapping("/sign-in")
    public WateryResponse<SignInResponseDto> signIn(
            @Parameter(hidden = true)
            @RequestHeader("Authorization") String socialAccessToken,
            @RequestBody SignInRequestDto request
    ) {
        SignInResponseDto response = authService.signIn(socialAccessToken, request);
        return WateryResponse.of(response, "로그인 성공");
    }
}
