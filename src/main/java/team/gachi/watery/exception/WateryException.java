package team.gachi.watery.exception;


import jakarta.validation.constraints.NotNull;

public class WateryException extends RuntimeException {
    @NotNull public final int statusCode;
    @NotNull
    public final String defaultMessage;
    public final String detailMessage;

    public WateryException(ExceptionCode exceptionCode) {
        this.statusCode = exceptionCode.getStatusCode();
        this.defaultMessage = exceptionCode.getMessage();
        this.detailMessage = "";
    }

    public WateryException(ExceptionCode exceptionCode, String message) {
        this.statusCode = exceptionCode.getStatusCode();
        this.defaultMessage = exceptionCode.getMessage();
        this.detailMessage = message;
    }

    public String getMessage() {
        if (detailMessage != null) {
            return  "[" + defaultMessage + "] " + detailMessage;
        }
        return defaultMessage;
    }
}
