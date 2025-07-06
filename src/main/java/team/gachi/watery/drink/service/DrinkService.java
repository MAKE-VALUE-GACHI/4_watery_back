package team.gachi.watery.drink.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.gachi.watery.drink.dto.AddDrinkRequest;
import team.gachi.watery.drink.dto.AddDrinkResponse;
import team.gachi.watery.drink.model.ColorTemplate;
import team.gachi.watery.drink.model.Drink;
import team.gachi.watery.drink.repository.ColorTemplateRepository;
import team.gachi.watery.drink.repository.DrinkRepository;
import team.gachi.watery.exception.ExceptionCode;
import team.gachi.watery.exception.WateryException;
import team.gachi.watery.user.User;
import team.gachi.watery.user.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DrinkService {
    private final DrinkRepository drinkRepository;
    private final UserRepository userRepository;
    private final ColorTemplateRepository colorTemplateRepository;

    @Transactional
    public AddDrinkResponse addDrink(Long userId, AddDrinkRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new WateryException(ExceptionCode.USER_NOT_FOUND));

        ColorTemplate colorTemplate = colorTemplateRepository.findById(request.colorTemplateId())
                .orElseThrow(() -> new WateryException(ExceptionCode.COLOR_TEMPLATE_NOT_FOUND));

        Drink drink = Drink.createDrinkOfUser(
                user,
                request.name(),
                colorTemplate,
                request.includesDailyHydrationGoal()
        );

        Drink savedDrink = drinkRepository.save(drink);

        return AddDrinkResponse.of(savedDrink.getId());
    }
}
