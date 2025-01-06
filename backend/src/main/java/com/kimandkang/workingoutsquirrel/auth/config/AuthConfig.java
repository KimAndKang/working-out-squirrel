package com.kimandkang.workingoutsquirrel.auth.config;

import static com.kimandkang.workingoutsquirrel.auth.interceptor.support.HttpMethod.OPTIONS;
import static com.kimandkang.workingoutsquirrel.auth.interceptor.support.HttpMethod.POST;

import com.kimandkang.workingoutsquirrel.auth.interceptor.AuthCheckInterceptor;
import com.kimandkang.workingoutsquirrel.auth.interceptor.BlackListInterceptor;
import com.kimandkang.workingoutsquirrel.auth.interceptor.PathMatchInterceptor;
import com.kimandkang.workingoutsquirrel.auth.annotation.AuthArgumentResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class AuthConfig implements WebMvcConfigurer {

    private final AuthArgumentResolver authArgumentResolver;
    private final AuthCheckInterceptor authCheckInterceptor;
    private final BlackListInterceptor blackListInterceptor;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor());
        registry.addInterceptor(tokenBlackListInterceptor());
    }

    private HandlerInterceptor authInterceptor() {
        return new PathMatchInterceptor(authCheckInterceptor)
                .excludePathPattern("/**", OPTIONS)
                .includePathPattern("/api/v1/oauth/reissue", POST)
                .includePathPattern("/api/v1/oauth/logout", POST)
                ;
    }

    private HandlerInterceptor tokenBlackListInterceptor() {
        return new PathMatchInterceptor(blackListInterceptor)
                .excludePathPattern("/**", OPTIONS)
                .includePathPattern("/api/v1/oauth/reissue", POST)
                .includePathPattern("/api/v1/oauth/logout", POST)
                ;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authArgumentResolver);
    }
}
