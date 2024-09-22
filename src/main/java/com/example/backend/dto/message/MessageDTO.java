package com.example.backend.dto.message;

import lombok.Data;

import javax.persistence.Column;

@Data
public class MessageDTO {
    private String senderId;

    private String receiverId;

    private String message;

    private String type;
    //Timestamp
    private Long date;

    private String chatId;

    public MessageDTO(String senderId, String receiverId, String message, String type ,Long date, String chatId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.type = type;
        this.date = date;
        this.chatId = chatId;
    }
}
