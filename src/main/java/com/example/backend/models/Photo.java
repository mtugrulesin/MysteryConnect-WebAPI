package com.example.backend.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name ="photos")
@Document
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "senderId", nullable = false, length = 50)
    private String senderId;

    @Column(name = "receiverId", nullable = false, length = 50)
    private String receiverId;

    @Column(name = "photoUrl",nullable = false,length = 250)
    private String photoUrl;

    @Column(name = "type")
    private String type;

    public Photo(String senderId, String receiverId, String photoUrl, String type) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.photoUrl = photoUrl;
        this.type = type;
    }
}
