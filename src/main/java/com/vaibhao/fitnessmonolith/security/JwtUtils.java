package com.vaibhao.fitnessmonolith.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    private String jwtSecret = "kZpnqm5OgBgpMxplO1n5pxXf9mk2l2ZP9rJTjp35dS1";
    private int jwtExpirationMs = 172800000;

    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);
        return null;
    }

    public String generateToken(String userId, String role) {

        return Jwts.builder()
                .subject(userId)
                .claim("roles", List.of(role))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    /**
     * Validates the JWT token and returns its claims.
     * This combines validation and parsing into a single operation
     * to avoid redundant expensive cryptographic signature verifications.
     *
     * @param jwtToken The JWT token string
     * @return Claims if the token is valid, null otherwise
     */
    public Claims validateAndGetClaims(String jwtToken) {
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(jwtToken)
                    .getPayload();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}