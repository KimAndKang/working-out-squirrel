package com.kimandkang.workingoutsquirrel.redis.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessToken {

    private String userId;
    private String claims;
}
