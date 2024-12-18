package com.kimandkang.workingoutsquirrel.redis.repository;

import com.kimandkang.workingoutsquirrel.redis.domain.RefreshToken;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<RefreshToken, String> {

    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByUserId(String userId);
}
