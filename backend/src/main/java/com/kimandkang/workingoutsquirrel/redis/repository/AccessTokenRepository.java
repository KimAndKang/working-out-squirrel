package com.kimandkang.workingoutsquirrel.redis.repository;

import com.kimandkang.workingoutsquirrel.redis.domain.AccessToken;
import org.springframework.data.repository.CrudRepository;

public interface AccessTokenRepository extends CrudRepository<AccessToken, Long> {

}
