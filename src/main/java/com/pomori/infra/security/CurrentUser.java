package com.pomori.infra.security;

import com.pomori.domain.exception.PomoriException;
import com.pomori.domain.user.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {

    public UserEntity get() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof UserEntity)) {
            throw new PomoriException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return (UserEntity) principal;
    }
}
