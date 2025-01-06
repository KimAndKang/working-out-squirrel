package com.kimandkang.workingoutsquirrel.redis.domain;

import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor
@RedisHash(value = "refresh", timeToLive = 604_800)
public class RefreshToken {

    @Id
    private Long userId;

    @Indexed
    private String claims;

    @Builder
    public RefreshToken(Long userId, String claims) {
        this.userId = userId;
        this.claims = claims;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RefreshToken that = (RefreshToken) o;
        return Objects.equals(getClaims(), that.getClaims());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClaims());
    }
}
