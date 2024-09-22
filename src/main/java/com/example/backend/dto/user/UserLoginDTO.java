package com.example.backend.dto.user;

import lombok.Getter;

@Getter
public class UserLoginDTO {
    private final String userName;

    private final String password;

    public UserLoginDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
