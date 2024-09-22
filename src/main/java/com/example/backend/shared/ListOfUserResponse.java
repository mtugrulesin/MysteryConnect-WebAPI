package com.example.backend.shared;

import com.example.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public final class ListOfUserResponse {

    private String message;
    private boolean success;
    private List<User> users;

}
