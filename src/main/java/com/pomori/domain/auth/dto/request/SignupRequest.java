package com.pomori.domain.auth.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record SignupRequest(

        @NotBlank
        @Size(min = 3, max = 20)
        String username,

        @NotBlank
        @Size(min = 8, max = 20)
        @Pattern(regexp = "^[A-Za-z\\d!@#$%&*]{8,20}$")
        String password
) {
}
