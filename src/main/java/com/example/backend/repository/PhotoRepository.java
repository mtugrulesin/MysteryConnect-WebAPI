package com.example.backend.repository;


import com.example.backend.models.Chat;
import com.example.backend.models.Photo;
import org.hibernate.annotations.Any;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PhotoRepository extends MongoRepository<Photo, Long> {

    Photo findByReceiverId(String receiverId);
    Photo findById(String id);

    Photo findByPhotoUrl(String photoUrl);

    List<Photo> findAll();

}
