package com.pomori.infra.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pomori.domain.exception.PomoriException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.NestedServletException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (NestedServletException e) {
            if (e.getCause() instanceof PomoriException pe) {
                resolveCustomException(request, response, pe);
            } else {
                resolveCustomException(request, response,
                        new PomoriException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause())
                );
            }
        }
    }

    @SneakyThrows(IOException.class)
    private void resolveCustomException(
            final HttpServletRequest req,
            final HttpServletResponse res,
            final PomoriException e
    ) {
        final var errorResponse = ErrorResponse.of(e);

        res.setStatus(e.getStatus().value());
        res.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        res.getWriter().write(
                objectMapper.writeValueAsString(errorResponse)
        );
        res.getWriter().flush();

        if (e.getStatus().is5xxServerError()) {
            logger.error(e.getMessage(), e);
        } else {
            logger.warn("[ID: " + errorResponse.errorId() + "] " + e.getMessage());
        }
    }
}
