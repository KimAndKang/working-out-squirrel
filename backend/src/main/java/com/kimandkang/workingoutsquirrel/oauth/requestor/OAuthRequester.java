package com.kimandkang.workingoutsquirrel.oauth.requestor;

import com.kimandkang.workingoutsquirrel.oauth.domain.OAuthUser;
import com.kimandkang.workingoutsquirrel.oauth.dto.OAuthLoginRequest;

public interface OAuthRequester {

    String loginUri(String providerName, String redirectUri);

    OAuthUser login(OAuthLoginRequest request, String provider);
}
