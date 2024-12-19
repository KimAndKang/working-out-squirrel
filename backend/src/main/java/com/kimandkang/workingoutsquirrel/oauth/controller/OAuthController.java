package com.kimandkang.workingoutsquirrel.oauth.controller;

import com.kimandkang.workingoutsquirrel.oauth.domain.OAuthUser;
import com.kimandkang.workingoutsquirrel.oauth.dto.OAuthLoginRequest;
import com.kimandkang.workingoutsquirrel.oauth.dto.OAuthLoginUriResponse;
import com.kimandkang.workingoutsquirrel.oauth.requestor.OAuthRequester;
import com.kimandkang.workingoutsquirrel.oauth.service.OAuthService;
import com.kimandkang.workingoutsquirrel.redis.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;
    private final OAuthRequester oAuthRequester;

    @GetMapping("/oauth/{providerName}/login-uri")
    public ResponseEntity<OAuthLoginUriResponse> loginUri(
            @PathVariable String providerName,
            @RequestParam("redirect-uri") String redirectUri
    ) {
        String loginUri = oAuthRequester.loginUri(providerName, redirectUri);
        return ResponseEntity.ok(new OAuthLoginUriResponse(loginUri));
    }

    @PostMapping("/oauth/{providerName}/login")
    public ResponseEntity<TokenResponse> login(
            @PathVariable String providerName,
            @RequestBody OAuthLoginRequest request
    ) {
        OAuthUser oAuthUser = oAuthRequester.login(request, providerName);
        TokenResponse tokenResponse = oAuthService.issueTokens(oAuthUser);
        return ResponseEntity.ok(tokenResponse);
    }
}
