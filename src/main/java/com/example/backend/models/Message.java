package com.example.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name ="messages")
@Document
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "senderId")
    private String senderId;

    @Column(name = "receiverId")
    private String receiverId;

    @Column(name = "message")
    private String message;

    @Column(name = "type")
    private String type;

    //Timestamp
    @Column(name = "date")
    private Long date;

    @Column(name = "chatId")
    private String chatId;

    public Message(String senderId, String receiverId, String message, String type, Long date, String chatId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.type = type;
        this.date = date;
        this.chatId = chatId;
    }
}
