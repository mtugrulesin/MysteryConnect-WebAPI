package com.example.backend.service.message;

import com.example.backend.dto.message.MessageDTO;
import com.example.backend.models.Message;
import com.example.backend.repository.MessageRepository;
import com.example.backend.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    final ChatService chatService;

    @Override
    @Transactional
    public boolean createMessage(Map<String,String> body) {
        boolean success = false;
        final Date date = new Date();
        final Message msg = messageRepository.save(
                new Message(
                        body.get("senderId"),
                        body.get("receiverId"),
                        body.get("context"),
                        "CHAT",
                        date.getTime()/1000,
                        body.get("chatId")
                )
        );

        if(msg.getId() != null){
            success = true;
            chatService.setUserUnReadMessage(body.get("receiverId"),body.get("chatId"));
        }


        return success;
    }

    @Override
    public List<Message> findMessageById(Long id) {

        List<Message> messages = messageRepository.findAll();
        final  List<Message> idsMessages = Collections.emptyList();

        if (messages.size() != 0){
            for (Message message : messages){
                if(message.getSenderId().equals(id)){
                    idsMessages.add(message);
                }
            }
        }

        return null;
    }
}
