package com.example.backend.api;


import com.example.backend.dto.message.MessageDTO;
import com.example.backend.models.Chat;
import com.example.backend.models.Message;
import com.example.backend.service.chat.ChatService;
import com.example.backend.service.message.MessageService;
import com.example.backend.shared.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageAPI {

    private final MessageService messageService;
    private final ChatService chatService;

    @PostMapping(
            path = "v1/message/createMessage",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<?> createMessage(@RequestParam Map<String,String> body){
        String message = "";
        boolean success = false;

        success = messageService.createMessage(
                body
        );




        if(success){
            message = "Message Create is Success";
        }

        return ResponseEntity.ok(success);
    }


    @GetMapping("v1/messages/{id}")
    public @ResponseBody ResponseEntity<List<Message>> getMyPhotos(@PathVariable Long id){

        final List<Message> myMessages = messageService.findMessageById(id);
        return ResponseEntity.ok(myMessages);

    }
}
