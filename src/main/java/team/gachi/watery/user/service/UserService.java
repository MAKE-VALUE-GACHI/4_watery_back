package team.gachi.watery.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.gachi.watery.exception.ExceptionCode;
import team.gachi.watery.exception.WateryException;
import team.gachi.watery.user.domain.User;
import team.gachi.watery.user.dto.UserProfileRequestDto;
import team.gachi.watery.user.dto.UserProfileResponseDto;
import team.gachi.watery.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void setUserProfile(long userId, UserProfileRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WateryException(ExceptionCode.NOT_FOUND));

        user.updateProfile(
                requestDto.gender(),
                requestDto.activityLevel(),
                requestDto.weight(),
                requestDto.yearOfBirth()
        );

        // TODO 수정 예정
        user.updateDailyHydrationGoal(2345);
    }

    public UserProfileResponseDto getUserProfile(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new WateryException(ExceptionCode.NOT_FOUND));

        return UserProfileResponseDto.of(user);
    }
}
