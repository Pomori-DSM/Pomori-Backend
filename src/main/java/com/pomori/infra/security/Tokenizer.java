package com.pomori.infra.security;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class Tokenizer {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();

    public String tokenize(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(key)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .compact();
    }

    public String parse(String token) {
        return parser.parseClaimsJws(token).getBody().getSubject();
    }
}
