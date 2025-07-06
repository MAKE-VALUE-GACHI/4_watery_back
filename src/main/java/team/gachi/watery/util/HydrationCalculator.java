package team.gachi.watery.util;

import team.gachi.watery.exception.ExceptionCode;
import team.gachi.watery.exception.WateryException;
import team.gachi.watery.user.domain.User;

public class HydrationCalculator {

    private static final int ML_PER_KG = 30;

    public static int calculate(int weight, User.ActivityLevel activityLevel) {
        int base = weight * ML_PER_KG;

        // 활동 수준에 따라 추가 보정값을 줄 수 있음 (임시)
        int bonus = switch (activityLevel) {
            case NONE -> throw new WateryException(ExceptionCode.INVALID_ENUM_TYPE);
            case LOW -> 0;
            case NORMAL -> 300;
            case ACTIVE -> 500;
            case VERY_ACTIVE -> 800;
        };

        return base + bonus; // 단위: ml
    }
}