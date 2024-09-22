package com.example.backend.shared;


import com.example.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class AuthenticationResponse {

    private String message;
    private boolean success;
    private User user;

}
