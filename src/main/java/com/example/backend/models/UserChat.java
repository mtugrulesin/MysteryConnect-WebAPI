package com.example.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name ="userchats")
public class UserChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "chatId")
    private Long chatId;

    public UserChat(Long userId,Long chatId){
        this.chatId = chatId;
        this.userId = userId;
    }

}
