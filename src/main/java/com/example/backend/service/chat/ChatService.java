package com.example.backend.service.chat;

import com.example.backend.models.Chat;
import com.example.backend.models.Message;

import java.util.List;

public interface ChatService {

    String createChat(String senderId,String receiverId);

    List<Chat> getUserChats(String id);

    boolean setChatProfile(Long id, Chat chat);

    void setChatTime(Long chatId, Long time);

    List<Message> getChatMessages(String chatId,String userId);

    boolean setUserUnReadMessage(String userId,String chatId);

    boolean setUserReadMessage(String userId,String chatId);
}
