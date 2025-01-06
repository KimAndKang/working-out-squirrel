package com.kimandkang.workingoutsquirrel.redis.service;

import static com.kimandkang.workingoutsquirrel.redis.exception.TokenExceptionInfo.NOT_EQUAL_REFRESH_TOKEN;
import static com.kimandkang.workingoutsquirrel.redis.exception.TokenExceptionInfo.NO_REFRESH_TOKEN;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import com.kimandkang.workingoutsquirrel.redis.domain.AccessToken;
import com.kimandkang.workingoutsquirrel.redis.domain.RefreshToken;
import com.kimandkang.workingoutsquirrel.redis.exception.TokenException;
import com.kimandkang.workingoutsquirrel.redis.repository.AccessTokenRepository;
import com.kimandkang.workingoutsquirrel.redis.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenRepository accessTokenRepository;

    public RefreshToken saveRefreshToken(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    public void validateRefreshToken(Long userId, String claims) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userId)
                .claims(claims)
                .build();
        RefreshToken savedRefreshToken = getRefreshTokenByUserId(userId);
        if (!refreshToken.equals(savedRefreshToken)) {
            throw new TokenException(NOT_EQUAL_REFRESH_TOKEN);
        }
    }

    private RefreshToken getRefreshTokenByUserId(Long userId) {
        return refreshTokenRepository.findById(userId)
                .orElseThrow(() -> new TokenException(NO_REFRESH_TOKEN));
    }

    public void deleteRefreshTokenByUserId(Long userId) {
        refreshTokenRepository.deleteById(userId);
    }

    public void deleteAllRefreshTokens() {
        refreshTokenRepository.deleteAll();
    }

    public boolean saveAccessToken(Long userId, String claims) {
        AccessToken accessToken = AccessToken.builder()
                .userId(userId)
                .claims(claims)
                .build();
        return accessTokenRepository.save(accessToken) != null ? TRUE : FALSE;
    }

    public boolean existsAccessTokenByUserId(Long userId) {
        return accessTokenRepository.existsById(userId);
    }
}
