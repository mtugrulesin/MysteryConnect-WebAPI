package com.example.backend.api;


import com.example.backend.models.Chat;
import com.example.backend.models.Message;
import com.example.backend.service.chat.ChatService;
import com.example.backend.shared.GenericResponse;
import com.example.backend.shared.ListOfMessageReponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatAPI {

    private final ChatService chatService;



    @PostMapping(
            path = "v1/chat/create",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<?> createChat(@RequestParam Map<String,String> body){
        String message = "Something Went Wrong";
        boolean isCreate = false;
        String chatId;
        chatId = chatService.createChat(body.get("senderId"), body.get("receiverId"));

        if(chatId != null){
            message = "Chat Room Created";
            isCreate = true;
        }

        return ResponseEntity.ok(new GenericResponse(message,isCreate));
    }


    @PostMapping(
            path = "v1/chat/getUserChats",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<List<Chat>> getUserChats(@RequestParam Map<String,String> body){

        return ResponseEntity.ok(chatService.getUserChats(body.get("userId")));
    }

    @PostMapping(
            path = "v1/chat/getChatMessages",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<?> getChatMessages(@RequestParam Map<String,String> body){

        ListOfMessageReponse lom = new ListOfMessageReponse("success",true,chatService.getChatMessages(body.get("chatId"),body.get("userId")));
        return ResponseEntity.ok(lom);
    }





}
