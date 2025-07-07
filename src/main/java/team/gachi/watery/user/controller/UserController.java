package team.gachi.watery.user.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.gachi.watery.dto.WateryResponse;
import team.gachi.watery.user.dto.UserProfileRequestDto;
import team.gachi.watery.user.dto.UserProfileResponseDto;
import team.gachi.watery.user.service.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/profile")
    public WateryResponse<?> setUserProfile(
            @Parameter(hidden = true) Principal principal,
            @RequestBody UserProfileRequestDto requestDto) {

        userService.setUserProfile(Long.parseLong(principal.getName()), requestDto);

        return WateryResponse.of("ok");
    }

    @GetMapping("/profile")
    public WateryResponse<UserProfileResponseDto> getUserProfile(
            @Parameter(hidden = true) Principal principal
    ) {
        UserProfileResponseDto response = userService.getUserProfile(Long.parseLong(principal.getName()));

        return WateryResponse.of(response);
    }
}
