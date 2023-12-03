package com.ciba.gameoptimizerapi.security;

import com.ciba.gameoptimizerapi.exceptions.UnauthorizedException;
import com.ciba.gameoptimizerapi.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.jooq.meta.derby.sys.Sys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.security.Key;
import java.util.UUID;
import java.util.function.Function;

@Component
@Setter
@Getter
public class JWTUtils {

    @Value("${app.expirationms}")
    private int expirationMs;

    @Value("${app.jwtsecret}")
    private String secret;

    public String getJWTFromHeader(HttpServletRequest request) throws UnauthorizedException {
        String headerValue = request.getHeader("Authorization");

        if (headerValue == null || !headerValue.startsWith("Bearer ")) {
            throw new UnauthorizedException("Token from request not available");
        }

        return headerValue.substring(7);
    }

    public String generateJWTToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("id", user.getId());
        claims.put("role", user.getRole());

        return createJWTToken(claims, user.getUsername());
    }

    private String createJWTToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationMs))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public UUID extractUUID(String token) {
        return UUID.fromString(extractClaims(token).get("id").toString());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        var date = extractClaim(token, Claims::getExpiration);
        return (username.equals(userDetails.getUsername()) && extractClaim(token, Claims::getExpiration).after(new Date()));
    }

    private Key getKey() { return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));}

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }
}
