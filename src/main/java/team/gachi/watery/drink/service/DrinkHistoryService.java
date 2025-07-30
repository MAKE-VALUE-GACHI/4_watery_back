package team.gachi.watery.drink.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.gachi.watery.drink.domain.Drink;
import team.gachi.watery.drink.domain.DrinkHistory;
import team.gachi.watery.drink.dto.AddDrinkHistoryRequestDto;
import team.gachi.watery.drink.dto.DailyDrinkHistoriesResponseDto;
import team.gachi.watery.drink.dto.DrinkHistoryDto;
import team.gachi.watery.drink.dto.RecentDrinkHistoryResponseDto;
import team.gachi.watery.drink.dto.WeeklyReportResponseDto;
import team.gachi.watery.drink.repository.DrinkHistoryRepository;
import team.gachi.watery.drink.repository.DrinkRepository;
import team.gachi.watery.exception.ExceptionCode;
import team.gachi.watery.exception.WateryException;
import team.gachi.watery.user.domain.User;
import team.gachi.watery.user.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        DrinkHistory drinkHistory = DrinkHistory.createDrinkHistory(user, drink,
                request.amount(), request.drinkAt());

        drinkHistoryRepository.save(drinkHistory);
    }

    public DailyDrinkHistoriesResponseDto getDailyDrinkHistories(Long userId, Long drinkId, LocalDate baseDate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WateryException(ExceptionCode.USER_NOT_FOUND));

        if (drinkId != null) {
            Drink drink = drinkRepository.findById(drinkId)
                    .orElseThrow(() -> new WateryException(ExceptionCode.DRINK_NOT_FOUND));

            if (!drink.isMyDrink(userId)) {
                throw new WateryException(ExceptionCode.FORBIDDEN);
            }
        }

        List<DrinkHistory> drinkHistories = drinkHistoryRepository.findBy(userId, drinkId, baseDate, baseDate);

        List<DrinkHistoryDto> drinkHistoryDtos = drinkHistories.stream()
                .map(DrinkHistoryDto::of)
                .collect(Collectors.toList());

        return new DailyDrinkHistoriesResponseDto(
                drinkHistoryDtos
        );
    }

    public WeeklyReportResponseDto getDrinkHistory(Long userId, LocalDate endDate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WateryException(ExceptionCode.USER_NOT_FOUND));

        LocalDate startDate = endDate.minusDays(7);
        List<DrinkHistory> drinkHistories = drinkHistoryRepository.findByUserIdAndDateRange(userId, startDate, endDate);

        // 날짜별로 그룹핑하여 합계
        Map<LocalDate, Integer> dateToAmount = drinkHistories.stream()
                .collect(Collectors.groupingBy(
                        h -> h.getCreatedAt().toLocalDate(),
                        Collectors.summingInt(DrinkHistory::getAmount)
                ));

        // 빠진 날짜는 0ml로 채워넣기
        List<WeeklyReportResponseDto.DailyConsumption> dailyConsumptions = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> {
                    LocalDate date = startDate.plusDays(i);
                    int amount = dateToAmount.getOrDefault(date, 0);
                    return new WeeklyReportResponseDto.DailyConsumption(date, amount);
                })
                .toList();

        return new WeeklyReportResponseDto(
                new WeeklyReportResponseDto.DateRange(startDate, endDate),
                dailyConsumptions
        );
    }

    public RecentDrinkHistoryResponseDto getRecentDrinkHistory(Long userId, Long drinkId, int limit) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WateryException(ExceptionCode.USER_NOT_FOUND));

        Drink drink = drinkRepository.findById(drinkId)
                .orElseThrow(() -> new WateryException(ExceptionCode.DRINK_NOT_FOUND));

        if (!drink.isMyDrink(userId)) {
            throw new WateryException(ExceptionCode.FORBIDDEN);
        }

        List<DrinkHistory> recentHistories = drinkHistoryRepository.findRecentByUserIdAndDrinkId(userId, drinkId, limit);

        List<Integer> amounts = recentHistories.stream()
                .map(DrinkHistory::getAmount)
                .toList();

        return new RecentDrinkHistoryResponseDto(amounts);
    }

    @Transactional
    public void deleteDrinkHistory(Long userId, Long drinkHistoryId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WateryException(ExceptionCode.USER_NOT_FOUND));

        DrinkHistory drinkHistory = drinkHistoryRepository.findById(drinkHistoryId)
                .orElseThrow(() -> new WateryException(ExceptionCode.DRINK_HISTORY_NOT_FOUND));

        if (drinkHistory.getUser().equals(user)) {
            throw new WateryException(ExceptionCode.FORBIDDEN);
        }

        drinkHistoryRepository.delete(drinkHistory);
    }
}
