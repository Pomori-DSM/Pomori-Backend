package com.pomori.domain.auth.dto.request;

public record SignupRequest(
        String username,
        String password
) {
}
