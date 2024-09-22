package com.example.backend.repository;

import com.example.backend.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, Long> {

    List<Message> findByChatId();
}
