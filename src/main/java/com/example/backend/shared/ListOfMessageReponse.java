package com.example.backend.shared;

import com.example.backend.models.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListOfMessageReponse {

    private String message;
    private boolean success;
    private List<Message> messages;
}
