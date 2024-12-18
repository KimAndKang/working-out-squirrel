package com.kimandkang.workingoutsquirrel.oauth.domain;

import static com.kimandkang.workingoutsquirrel.oauth.exception.OAuthExceptionInfo.UNSUPPORTED_PROVIDER;

import com.kimandkang.workingoutsquirrel.oauth.exception.OAuthException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public enum Provider {

    GOOGLE("google", GoogleUser::new),
    KAKAO("kakao", KakaoUser::new),
    NAVER("naver", NaverUser::new);

    private final String providerName;
    private final Function<Map<String, Object>, OAuthUser> function;

    Provider(String providerName, Function<Map<String, Object>, OAuthUser> function) {
        this.providerName = providerName;
        this.function = function;
    }

    public static Provider from(String name) {
        return Arrays.stream(values())
                .filter(it -> it.providerName.equals(name))
                .findFirst()
                .orElseThrow(() -> new OAuthException(UNSUPPORTED_PROVIDER));
    }

    public OAuthUser getOAuthUser(Map<String, Object> body) {
        return function.apply(body);
    }
}
