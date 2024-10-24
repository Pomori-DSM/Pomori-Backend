package com.pomori.infra.exception;

import com.pomori.domain.exception.PomoriException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp,
        String errorId
) {

    public static ErrorResponse of(PomoriException e) {
        return new ErrorResponse(
                e.getStatus().value(),
                e.getStatus().is5xxServerError() ?
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() : e.getMessage(),
                LocalDateTime.now(),
                UUID.randomUUID().toString().substring(0, 7)
        );
    }
}
