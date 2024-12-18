package com.kimandkang.workingoutsquirrel.oauth.domain;

import java.util.Map;

public class GoogleUser implements OAuthUser {

    private final String name;
    private final String gender;
    private final String email;
    private final String imageURL;

    public GoogleUser(Map<String, Object> attribute) {
        this.name = (String) attribute.get("name");
        this.gender = (String) attribute.getOrDefault("gender", null);
        this.email = (String) attribute.get("email");
        this.imageURL = (String) attribute.get("picture");
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
        return imageURL;
    }
}
