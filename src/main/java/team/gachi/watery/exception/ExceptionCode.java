package team.gachi.watery.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

    // ===== 4xx =====
    UNAUTHORIZED(401, "유효하지 않은 토큰"),
    FORBIDDEN(403, "접근 권한이 없습니다."),

    // --- 404 Not Found ---
    NOT_FOUND(404, "존재하지 않음"),
    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    COLOR_TEMPLATE_NOT_FOUND(404, "색상 템플릿을 찾을 수 없습니다."),
    DRINK_NOT_FOUND(404, "음료 정보를 찾을 수 없습니다."),
    DRINK_HISTORY_NOT_FOUND(404, "음료 기록을 찾을 수 없습니다."),

    // --- 400 Bad Request ---
    INVALID_INPUT(400, "입력값이 올바르지 않습니다."),
    INVALID_ENUM_TYPE(400, "유효하지 않은 Enum 값"),
    DRINK_NAME_ALREADY_EXISTS(400, "이미 존재하는 음료 이름입니다."),

    // ===== 5xx =====
    SERVICE_AVAILABLE(503, "서비스에 접근할 수 없음"),
    INTERNAL_SERVER_ERROR(500, "서버 내부 오류"),
    JSON_PARSE_ERROR(503, "json 파싱 오류. 서버에게 문의해주세요."),
    ;

    private final int statusCode;
    private final String message;
}
