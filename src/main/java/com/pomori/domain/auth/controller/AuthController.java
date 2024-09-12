package com.pomori.domain.auth.controller;

import com.pomori.domain.auth.dto.request.SignupRequest;
import com.pomori.domain.auth.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final SignupService signupService;

    @PostMapping("/signup")
    void signup(
            @RequestBody
            SignupRequest signupRequest
    ) {
        signupService.signup(signupRequest);
    }

}
