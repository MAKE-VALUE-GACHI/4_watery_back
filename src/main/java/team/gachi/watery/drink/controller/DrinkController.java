package team.gachi.watery.drink.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.gachi.watery.auth.UserAuthentication;
import team.gachi.watery.drink.dto.AddDrinkRequest;
import team.gachi.watery.drink.dto.AddDrinkResponse;
import team.gachi.watery.drink.service.DrinkService;
import team.gachi.watery.dto.WateryResponse;

@Tag(name = "음료", description = "음료 관련 API")
@RequiredArgsConstructor
@RestController("/api/v1/drinks")
public class DrinkController {
    private final DrinkService drinkService;

    @Operation(summary = "음료 추가", description = "새로운 음료를 추가합니다.")
    @PostMapping
    public WateryResponse<AddDrinkResponse> addDrink(
            @AuthenticationPrincipal
            UserAuthentication authentication,

            @RequestBody
            AddDrinkRequest request
    ) {
        AddDrinkResponse response = drinkService.addDrink(authentication.getUserId(), request);

        return WateryResponse.of(response, "음료 추가 성공");
    }
}
