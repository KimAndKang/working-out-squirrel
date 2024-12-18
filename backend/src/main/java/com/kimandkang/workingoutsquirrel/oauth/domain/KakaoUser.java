package com.kimandkang.workingoutsquirrel.oauth.domain;

import java.util.Map;

public class KakaoUser implements OAuthUser {

    private final String name;
    private final String gender;
    private final String email;
    private final String imageUrl;

    public KakaoUser(Map<String, Object> attribute) {
        Map<String, String> properties = (Map) attribute.get("properties");
        Map<String, Object> kakaoAccount = (Map) attribute.get("kakao_account");
        this.name = properties.get("nickname");
        this.gender = properties.get("gender");
        this.email = (String) kakaoAccount.get("email");
        this.imageUrl = properties.get("profile_image_url");
    }

    @Override
    public String nickname() {
        return name;
    }

    @Override
    public String gender() {
        return gender;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String imageUrl() {
        return imageUrl;
    }
}