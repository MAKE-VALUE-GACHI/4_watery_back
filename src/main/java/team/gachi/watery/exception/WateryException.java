package team.gachi.watery.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WateryException extends RuntimeException {
    private final HttpStatus statusCode;

    public WateryException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
