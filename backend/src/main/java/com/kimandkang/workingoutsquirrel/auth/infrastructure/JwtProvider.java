package com.kimandkang.workingoutsquirrel.auth.infrastructure;

import static com.kimandkang.workingoutsquirrel.auth.exception.AuthExceptionInfo.EXPIRED_TOKEN;
import static com.kimandkang.workingoutsquirrel.auth.exception.AuthExceptionInfo.ILLEGAL_ARGUMENT;
import static com.kimandkang.workingoutsquirrel.auth.exception.AuthExceptionInfo.MALFORMED_TOKEN;
import static com.kimandkang.workingoutsquirrel.auth.exception.AuthExceptionInfo.SIGNATURE_NOT_FOUND;
import static com.kimandkang.workingoutsquirrel.auth.exception.AuthExceptionInfo.UNSUPPORTED_TOKEN;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;

import com.kimandkang.workingoutsquirrel.auth.exception.AuthException;
import com.kimandkang.workingoutsquirrel.redis.domain.AccessToken;
import com.kimandkang.workingoutsquirrel.redis.domain.RefreshToken;
import com.kimandkang.workingoutsquirrel.redis.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;
    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;
    private Key key;

    private final TokenService tokenService;

    @PostConstruct
    private void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public AccessToken issueAccessToken(Long userId) {
        String claims = Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(issuedAt())
                .setExpiration(accessTokenExpiration())
                .signWith(key, HS256)
                .compact();
        return AccessToken.builder()
                .userId(userId)
                .claims(claims)
                .build();
    }

    public RefreshToken issueRefreshToken(Long userId) {
        String claims = Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(issuedAt())
                .setExpiration(refreshTokenExpiration())
                .signWith(key, HS256)
                .compact();
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userId)
                .claims(claims)
                .build();
        return tokenService.saveRefreshToken(refreshToken);
    }

    private Date issuedAt() {
        LocalDateTime now = LocalDateTime.now();
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Date accessTokenExpiration() {
        LocalDateTime now = LocalDateTime.now();
        return Date.from(now
                .plusSeconds(accessTokenExpiration)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    private Date refreshTokenExpiration() {
        LocalDateTime now = LocalDateTime.now();
        return Date.from(now
                .plusSeconds(refreshTokenExpiration)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public Long extractId(String claimz) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes())
                    .build()
                    .parseClaimsJws(claimz)
                    .getBody();
            return claims.get("userId", Long.class);
        } catch (ExpiredJwtException e) {
            throw new AuthException(EXPIRED_TOKEN);
        } catch (SecurityException e) {
            throw new AuthException(SIGNATURE_NOT_FOUND);
        } catch (MalformedJwtException e) {
            throw new AuthException(MALFORMED_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new AuthException(UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException e) {
            throw new AuthException(ILLEGAL_ARGUMENT);
        }
    }
}
