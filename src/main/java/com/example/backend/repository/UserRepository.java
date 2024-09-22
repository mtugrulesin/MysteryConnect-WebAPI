package com.example.backend.repository;

import com.example.backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {

    User findById(String id);
    User findByEmail(String email);

    List<User> findByGender(String gender);
    //Boolean existsByUsername(String username);
    //Boolean existsByEmail(String email);
}
