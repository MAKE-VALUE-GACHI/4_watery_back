package team.gachi.watery.user.dto;


import team.gachi.watery.user.domain.User;

public record UserProfileRequestDto(
        User.Gender gender,
        int yearOfBirth,
        int weight,
        User.ActivityLevel activityLevel
) {
}
