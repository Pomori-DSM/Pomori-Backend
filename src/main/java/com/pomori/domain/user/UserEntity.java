package com.pomori.domain.user;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "tbl_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "username", unique = true, updatable = true, nullable = false)
    private String username;

    @Column(name = "password", updatable = true, nullable = false)
    private String password;

    @Column(name = "created_at", updatable = true, nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
