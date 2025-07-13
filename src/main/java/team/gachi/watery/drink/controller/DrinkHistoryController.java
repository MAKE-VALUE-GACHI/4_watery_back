package team.gachi.watery.drink.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.gachi.watery.drink.dto.AddDrinkHistoryRequestDto;
import team.gachi.watery.drink.service.DrinkHistoryService;
import team.gachi.watery.dto.WateryResponse;

import java.security.Principal;

@Tag(name = "음료 섭취 기록", description = "음료 섭취 기록 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/drink-histories")
public class DrinkHistoryController {
    private final DrinkHistoryService drinkHistoryService;

    @Operation(summary = "음료 섭취 기록 추가", description = "음료 섭취 기록을 추가합니다.")
    @PostMapping
    public WateryResponse<?> addDrinkHistory(
            @Parameter(hidden = true)
            Principal principal,

            @Valid @RequestBody
            AddDrinkHistoryRequestDto request
    ) {
        drinkHistoryService.addDrinkHistory(Long.valueOf(principal.getName()), request);

        return WateryResponse.of("ok");
    }
}
