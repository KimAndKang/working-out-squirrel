package com.kimandkang.workingoutsquirrel.oauth.dto;

public record OAuthLoginRequest(
        String redirectUri,
        String code
) {

}
