package com.rk.springsecurity.repo;

import com.rk.springsecurity.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityServiceRepository extends MongoRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

}
