package team.gachi.watery.user.dto;


import team.gachi.watery.user.domain.User;

public record UserProfileResponseDto(
        User.SocialType socialType,
        String socialId,
        User.Gender gender,
        int yearOfBirth,
        int weight,
        User.ActivityLevel activityLevel,
        int dailyHydrationGoal
) {
    public static UserProfileResponseDto of(User user) {
        return new UserProfileResponseDto(
                user.getSocialType(),
                user.getSocialId(),
                user.getGender(),
                user.getYearOfBirth(),
                user.getWeight(),
                user.getActivityLevel(),
                user.getDailyHydrationGoal()
        );
    }
}
