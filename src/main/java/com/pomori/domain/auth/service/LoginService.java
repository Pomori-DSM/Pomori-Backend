package com.pomori.domain.auth.service;

import com.pomori.domain.annotation.ReadOnlyUseCase;
import com.pomori.domain.auth.dto.request.AuthRequest;
import com.pomori.domain.auth.dto.response.TokenResponse;
import com.pomori.domain.exception.PomoriException;
import com.pomori.domain.user.repository.UserRepository;
import com.pomori.infra.security.Tokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class LoginService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final Tokenizer tokenizer;

    public TokenResponse login(final AuthRequest request) {

        final var user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new PomoriException(HttpStatus.NOT_FOUND, "User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new PomoriException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }

        return new TokenResponse(
                tokenizer.tokenize(user.getUsername())
        );
    }
}
