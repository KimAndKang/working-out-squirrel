package com.kimandkang.workingoutsquirrel.redis.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@RedisHash(value = "refresh", timeToLive = 604_800)
public class RefreshToken {

    @Id
    private String userId;

    @Indexed
    private String claims;
}
