package com.kimandkang.workingoutsquirrel.oauth.service;

import com.kimandkang.workingoutsquirrel.auth.infrastructure.JwtProvider;
import com.kimandkang.workingoutsquirrel.oauth.domain.OAuthUser;
import com.kimandkang.workingoutsquirrel.redis.domain.AccessToken;
import com.kimandkang.workingoutsquirrel.redis.domain.RefreshToken;
import com.kimandkang.workingoutsquirrel.redis.dto.TokenResponse;
import com.kimandkang.workingoutsquirrel.user.domain.User;
import com.kimandkang.workingoutsquirrel.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Transactional
    public TokenResponse issueTokens(OAuthUser oAuthUser) {
        User user = User.builder()
                .name(oAuthUser.nickname())
                .email(oAuthUser.email())
                .gender(oAuthUser.gender())
                .imageUrl(oAuthUser.imageUrl())
                .build();

        User savedUser = userRepository.findByEmail(oAuthUser.email())
                .orElseGet(() -> userRepository.save(user));

        Long userId = savedUser.getId();
        AccessToken accessToken = jwtProvider.issueAccessToken(userId);
        RefreshToken refreshToken = jwtProvider.issueRefreshToken(userId);
        return new TokenResponse(accessToken, refreshToken);
    }
}
