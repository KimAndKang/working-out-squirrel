package com.kimandkang.workingoutsquirrel.oauth.domain;

import java.util.Map;

public class NaverUser implements OAuthUser {

    private final String name;
    private final String gender;
    private final String email;
    private final String imageUrl;

    public NaverUser(Map<String, Object> attribute) {
        Map<String, String> response = (Map) attribute.get("response");
        this.name = response.get("name");
        this.gender = response.get("gender");
        this.email = response.get("email");
        this.imageUrl = response.get("profile_image");
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