package com.kimandkang.workingoutsquirrel.redis.repository;

import com.kimandkang.workingoutsquirrel.redis.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

}
