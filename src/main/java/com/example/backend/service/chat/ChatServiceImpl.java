package com.example.backend.service.chat;


import com.example.backend.models.Chat;
import com.example.backend.models.Message;
import com.example.backend.repository.ChatRepository;
import com.example.backend.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {


    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    @Override
    public String createChat(String senderId, String receiverId) {
        boolean isCreated= false;
        ArrayList members = new ArrayList<>();
        members.add(senderId);
        members.add(receiverId);
        Map<String, Boolean> isRead = new HashMap<>();

        isRead.put(senderId,false);
        isRead.put(receiverId,false);

        final Chat chat = chatRepository.save(new Chat(
                members,
                isRead
        ));

        if(chat.getId() != null){
            isCreated = true;

        }

        return chat.getId();
    }


    @Override
    public List<Message> getChatMessages(String chatId, String userId) {
        List<Message> allMessages = messageRepository.findAll();
        List<Message> chatMessages = new ArrayList<>();

        Optional<Chat> chat = chatRepository.findById(chatId) ;

        for(Message message: allMessages){

            if(message.getChatId().equals(chatId) && message.getReceiverId().equals(userId)){
                chatMessages.add(message);

                if(message.getReceiverId().equals(userId)){
                    messageRepository.delete(message);
                    setUserReadMessage(userId,chatId);
                    System.out.println("Silindi");
                }

            }

        }
        return chatMessages;
    }

    @Override
    public boolean setUserUnReadMessage(String userId, String chatId) {
        Optional<Chat> chat = chatRepository.findById(chatId);

        Map<String,Boolean> tempMap = chat.get().getIsRead();

        tempMap.put(userId,false);

        chat.get().setIsRead(tempMap);

        chatRepository.save(chat.get());

        return false;
    }

    @Override
    public boolean setUserReadMessage(String userId, String chatId) {
        Optional<Chat> chat = chatRepository.findById(chatId);

        Map<String,Boolean> tempMap = chat.get().getIsRead();

        tempMap.put(userId,true);

        chat.get().setIsRead(tempMap);

        chatRepository.save(chat.get());

        return false;
    }

    @Override
    public List<Chat> getUserChats(String userId) {

        List<Chat> allChats = chatRepository.findAll();
        List<Chat> userChats = new ArrayList<>();

        for(Chat chat: allChats){
            List<String> members = chat.getMembers();
            if(members.contains(userId)){
                userChats.add(chat);
            }

        }


        return userChats;
    }



    @Override
    public boolean setChatProfile(Long id, Chat chat) {

//        Chat chatTemp = chatRepository.findById(id);
//
//        chatTemp.setReceiverProfile(chat.getReceiverProfile());
//        chatTemp.setSenderProfile(chat.getSenderProfile());
//
//        chatRepository.save(chatTemp);

        return false;
    }

    @Override
    public void setChatTime(Long chatId, Long time) {

//        Chat chatTemp = chatRepository.findById(id);
//
//        chatTemp.setLastMessageTime(chat.getLastMessageTime());
//
//        chatRepository.save(chatTemp);

    }
}
