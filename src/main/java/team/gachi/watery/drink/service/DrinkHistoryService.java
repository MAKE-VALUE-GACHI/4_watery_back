package team.gachi.watery.drink.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.gachi.watery.drink.domain.Drink;
import team.gachi.watery.drink.domain.DrinkHistory;
import team.gachi.watery.drink.dto.AddDrinkHistoryRequestDto;
import team.gachi.watery.drink.repository.DrinkHistoryRepository;
import team.gachi.watery.drink.repository.DrinkRepository;
import team.gachi.watery.exception.ExceptionCode;
import team.gachi.watery.exception.WateryException;
import team.gachi.watery.user.domain.User;
import team.gachi.watery.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DrinkHistoryService {
    private final DrinkRepository drinkRepository;
    private final UserRepository userRepository;
    private final DrinkHistoryRepository drinkHistoryRepository;


    @Transactional
    public void addDrinkHistory(Long userId, AddDrinkHistoryRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WateryException(ExceptionCode.USER_NOT_FOUND));

        Drink drink = drinkRepository.findById(request.drinkId())
                .orElseThrow(() -> new WateryException(ExceptionCode.DRINK_NOT_FOUND));

        if (!drink.isMyDrink(userId)) {
            throw new WateryException(ExceptionCode.FORBIDDEN);
        }

        DrinkHistory drinkHistory = DrinkHistory.CreateDrinkHistory(user, drink,
                request.amount(), request.drinkAt());

        drinkHistoryRepository.save(drinkHistory);
    }
}
