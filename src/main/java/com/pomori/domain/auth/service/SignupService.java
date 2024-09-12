package com.pomori.domain.auth.service;

import com.pomori.domain.annotation.UseCase;
import com.pomori.domain.auth.dto.request.SignupRequest;
import com.pomori.domain.exception.PomoriException;
import com.pomori.domain.user.UserEntity;
import com.pomori.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@UseCase
public class SignupService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void signup(final SignupRequest request) {

        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new PomoriException(HttpStatus.CONFLICT, "Username already exists");
        }

        userRepository.save(
                UserEntity.builder()
                        .username(request.username())
                        .password(passwordEncoder.encode(request.password()))
                        .build()
        );
    }
}
