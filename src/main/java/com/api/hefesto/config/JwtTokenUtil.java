package com.api.hefesto.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenUtil {
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.key-system}")
    private String keySystem;

    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
    
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public boolean isTokenValid(String token) {
        String keyToken = getClaimsFromToken(token).getSubject();
        return keyToken.contains(keySystem) && !isTokenExpired(token);
    }
}
