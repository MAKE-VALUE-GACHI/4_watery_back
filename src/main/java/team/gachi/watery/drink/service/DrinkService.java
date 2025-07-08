package team.gachi.watery.drink.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.gachi.watery.drink.dto.AddDrinkRequestDto;
import team.gachi.watery.drink.dto.AddDrinkResponseDto;
import team.gachi.watery.drink.dto.DrinkDto;
import team.gachi.watery.drink.dto.DrinksResponseDto;
import team.gachi.watery.drink.domain.ColorTemplate;
import team.gachi.watery.drink.domain.Drink;
import team.gachi.watery.drink.repository.ColorTemplateRepository;
import team.gachi.watery.drink.repository.DrinkHistoryRepository;
import team.gachi.watery.drink.repository.DrinkRepository;
import team.gachi.watery.exception.ExceptionCode;
import team.gachi.watery.exception.WateryException;
import team.gachi.watery.user.domain.User;
import team.gachi.watery.user.repository.UserRepository;
import team.gachi.watery.util.StringUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DrinkService {
    private final DrinkRepository drinkRepository;
    private final UserRepository userRepository;
    private final ColorTemplateRepository colorTemplateRepository;
    private final DrinkHistoryRepository drinkHistoryRepository;

    @Transactional
    public AddDrinkResponseDto addDrink(Long userId, AddDrinkRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WateryException(ExceptionCode.USER_NOT_FOUND));

        String drinkName;
        ColorTemplate colorTemplate;

        if (request.drinkTemplateId() != null) {
            if (StringUtil.isBlank(request.name()) || request.colorTemplateId() == null) {
                throw new WateryException(ExceptionCode.INVALID_INPUT);
            }

            drinkName = request.name();
            colorTemplate = colorTemplateRepository.findById(request.colorTemplateId())
                    .orElseThrow(() -> new WateryException(ExceptionCode.COLOR_TEMPLATE_NOT_FOUND));
        } else {
            Drink drinkTemplate = drinkRepository.findById(request.drinkTemplateId())
                    .orElseThrow(() -> new WateryException(ExceptionCode.DRINK_NOT_FOUND));

            drinkName = drinkTemplate.getName();
            colorTemplate = drinkTemplate.getColorTemplate();
        }

        if (drinkRepository.existsByUserIdAndName(userId, drinkName)) {
            throw new WateryException(ExceptionCode.DRINK_NAME_ALREADY_EXISTS);
        }

        Drink drink = Drink.createDrinkOfUser(
                user,
                drinkName,
                colorTemplate,
                request.includesDailyHydrationGoal()
        );

        Drink savedDrink = drinkRepository.save(drink);

        return AddDrinkResponseDto.of(savedDrink.getId());
    }

    public DrinksResponseDto getDrinks(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WateryException(ExceptionCode.USER_NOT_FOUND));

        List<Drink> drinks = drinkRepository.findAllByUserId(user.getId());

        List<DrinkDto> drinkDtos = drinks.stream()
                .map(DrinkDto::of)
                .toList();

        return DrinksResponseDto.of(
                user.getDailyHydrationGoal(),
                drinkDtos
        );
    }
}
