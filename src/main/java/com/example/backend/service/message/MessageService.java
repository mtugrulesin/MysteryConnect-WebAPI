package com.example.backend.service.message;

import com.example.backend.dto.message.MessageDTO;
import com.example.backend.models.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {
    boolean createMessage(Map<String,String> body);

    List<Message> findMessageById(Long id);

}
