package team.gachi.watery.dto;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record WateryResponse<T>(
        boolean success,
        String message,
        T data
) {

    public static <T> WateryResponse<T> of(T data, String message) {
        return WateryResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static WateryResponse<?> of(String message) {
        return WateryResponse.builder()
                .success(true)
                .message(message)
                .build();
    }
}
