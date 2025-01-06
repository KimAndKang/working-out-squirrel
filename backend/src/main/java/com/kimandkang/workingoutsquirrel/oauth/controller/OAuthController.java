package com.kimandkang.workingoutsquirrel.oauth.controller;

import com.kimandkang.workingoutsquirrel.auth.annotation.AuthUser;
import com.kimandkang.workingoutsquirrel.auth.infrastructure.JwtProvider;
import com.kimandkang.workingoutsquirrel.oauth.domain.OAuthUser;
import com.kimandkang.workingoutsquirrel.oauth.dto.LogoutRequest;
import com.kimandkang.workingoutsquirrel.oauth.dto.LogoutResponse;
import com.kimandkang.workingoutsquirrel.oauth.dto.OAuthLoginRequest;
import com.kimandkang.workingoutsquirrel.oauth.dto.OAuthLoginUriResponse;
import com.kimandkang.workingoutsquirrel.oauth.dto.ReissueRequest;
import com.kimandkang.workingoutsquirrel.oauth.requestor.OAuthRequester;
import com.kimandkang.workingoutsquirrel.oauth.service.OAuthService;
import com.kimandkang.workingoutsquirrel.redis.domain.AccessToken;
import com.kimandkang.workingoutsquirrel.redis.domain.RefreshToken;
import com.kimandkang.workingoutsquirrel.redis.dto.TokenResponse;
import com.kimandkang.workingoutsquirrel.redis.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;
    private final OAuthRequester oAuthRequester;
    private final TokenService tokenService;
    private final JwtProvider jwtProvider;

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

    @PostMapping("/oauth/reissue")
    public ResponseEntity<TokenResponse> reissue(
            @AuthUser Long userId,
            @RequestBody ReissueRequest request
    ) {
        tokenService.validateRefreshToken(userId, request.refreshToken());
        AccessToken accessToken = jwtProvider.issueAccessToken(userId);
        RefreshToken refreshToken = jwtProvider.issueRefreshToken(userId);
        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
    }

    @PostMapping("/oauth/logout")
    public ResponseEntity<LogoutResponse> logout(
            @AuthUser Long userId,
            @RequestBody LogoutRequest request
    ) {
        tokenService.deleteRefreshTokenByUserId(userId);
        boolean isBlackListed = tokenService.saveAccessToken(userId, request.accessToken());
        return ResponseEntity.ok(new LogoutResponse(isBlackListed));
    }
}
