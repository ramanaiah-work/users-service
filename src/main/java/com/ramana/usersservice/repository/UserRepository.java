package com.ramana.usersservice.repository;

import com.ramana.usersservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsernameOrEmail(String username, String email);
}
