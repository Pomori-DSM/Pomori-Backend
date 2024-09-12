package com.pomori.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class PomoriException extends RuntimeException {

    @Getter
    private final HttpStatus status;

    private final String message;

    @Override
    public String getMessage() {
        return message;
    }

    public PomoriException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public PomoriException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.message = message;
    }
}
