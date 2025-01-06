package com.kimandkang.workingoutsquirrel.auth.interceptor;

import static com.kimandkang.workingoutsquirrel.redis.exception.TokenExceptionInfo.BLACKLISTED_ACCESS_TOKEN;

import com.kimandkang.workingoutsquirrel.auth.infrastructure.JwtProvider;
import com.kimandkang.workingoutsquirrel.auth.annotation.AuthenticationExtractor;
import com.kimandkang.workingoutsquirrel.redis.exception.TokenException;
import com.kimandkang.workingoutsquirrel.redis.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class BlackListInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;
    private final TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String claims = AuthenticationExtractor.extractAccessToken(request).get();
        Long userId = jwtProvider.extractId(claims);
        boolean isBlackListed = tokenService.existsAccessTokenByUserId(userId);
        if (claims != null && isBlackListed) {
            throw new TokenException(BLACKLISTED_ACCESS_TOKEN);
        }
        return true;
    }
}
