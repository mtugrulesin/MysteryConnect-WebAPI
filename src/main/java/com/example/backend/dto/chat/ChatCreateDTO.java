package com.example.backend.dto.chat;

import lombok.Data;

@Data
public class ChatCreateDTO {
    private Long senderId;
    private Long receiverId;
    private String chatCode;

    public ChatCreateDTO(Long senderId, Long receiverId, String chatCode) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.chatCode = chatCode;
    }
}
