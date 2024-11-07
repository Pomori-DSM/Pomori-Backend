package com.pomori.domain.auth.controller;

import com.pomori.domain.auth.dto.request.AuthRequest;
import com.pomori.domain.auth.dto.response.TokenResponse;
import com.pomori.domain.auth.service.LoginService;
import com.pomori.domain.auth.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Validated
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final SignupService signupService;

    private final LoginService loginService;

    @PostMapping("/signup")
    public void signup(
            @Valid
            @NotNull
            @RequestBody
            AuthRequest authRequest
    ) {
        signupService.signup(authRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(
            @Valid
            @NotNull
            @RequestBody
            AuthRequest authRequest
    ) {
        return loginService.login(authRequest);
    }
}
