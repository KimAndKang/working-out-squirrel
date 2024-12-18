package com.kimandkang.workingoutsquirrel.redis.service;

import static com.kimandkang.workingoutsquirrel.redis.exception.TokenExceptionInfo.NO_SAVE_REFRESH_TOKEN;

import com.kimandkang.workingoutsquirrel.redis.domain.RefreshToken;
import com.kimandkang.workingoutsquirrel.redis.exception.TokenException;
import com.kimandkang.workingoutsquirrel.redis.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public RefreshToken saveRefreshToken(RefreshToken refreshToken) {
        tokenRepository.save(refreshToken);
        return tokenRepository.findByUserId(refreshToken.getUserId())
                .orElseThrow(() -> new TokenException(NO_SAVE_REFRESH_TOKEN));
    }
}
