package com.kimandkang.workingoutsquirrel.auth.interceptor;

import static com.kimandkang.workingoutsquirrel.auth.exception.AuthExceptionInfo.*;

import com.kimandkang.workingoutsquirrel.auth.exception.AuthException;
import com.kimandkang.workingoutsquirrel.auth.infrastructure.JwtProvider;
import com.kimandkang.workingoutsquirrel.auth.annotation.AuthenticationContext;
import com.kimandkang.workingoutsquirrel.auth.annotation.AuthenticationExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class AuthCheckInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;
    private final AuthenticationContext authenticationContext;
    private final BlackListInterceptor blackListInterceptor;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String claims = AuthenticationExtractor.extractAccessToken(request)
                .orElseThrow(() -> new AuthException(AUTHORIZATION_FAIL));
        Long userId = jwtProvider.extractId(claims);
        authenticationContext.setAuthentication(userId);
        return blackListInterceptor.preHandle(request, response, handler);
    }
}
