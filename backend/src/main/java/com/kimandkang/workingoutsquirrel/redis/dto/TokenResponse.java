package com.kimandkang.workingoutsquirrel.redis.dto;

import com.kimandkang.workingoutsquirrel.redis.domain.AccessToken;
import com.kimandkang.workingoutsquirrel.redis.domain.RefreshToken;

public record TokenResponse(
        AccessToken accessToken,
        RefreshToken refreshToken
) {
}
