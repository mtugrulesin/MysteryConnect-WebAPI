package com.example.backend.repository;

import com.example.backend.models.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRepository extends MongoRepository<Chat, String> {



}
