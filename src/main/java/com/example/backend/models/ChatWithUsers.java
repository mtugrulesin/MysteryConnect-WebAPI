package com.example.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class ChatWithUsers {

    private Chat chat;
    private User senderUser;
    private User receiverUser;

    public ChatWithUsers(Chat chat,User senderUser, User receiverUser) {
        this.chat = chat;
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
    }
}
