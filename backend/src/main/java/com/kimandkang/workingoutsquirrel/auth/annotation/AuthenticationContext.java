package com.kimandkang.workingoutsquirrel.auth.annotation;

import static com.kimandkang.workingoutsquirrel.auth.exception.AuthExceptionInfo.AUTHORIZATION_FAIL;

import com.kimandkang.workingoutsquirrel.auth.exception.AuthException;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class AuthenticationContext {

    private Long userId;

    public void setAuthentication(Long userId) {
        this.userId = userId;
    }

    public Long getAuthentication() {
        if (Objects.isNull(this.userId)) {
            throw new AuthException(AUTHORIZATION_FAIL);
        }
        return userId;
    }
}

