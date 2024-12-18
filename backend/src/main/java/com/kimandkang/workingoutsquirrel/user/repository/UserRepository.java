package com.kimandkang.workingoutsquirrel.user.repository;

import com.kimandkang.workingoutsquirrel.user.domain.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
