package com.example.backend.dto.user;

import com.example.backend.models.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public final class UserViewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String fullName;


    private UserViewDTO(String fullName) {
        this.fullName = fullName;
    }

    public static UserViewDTO of(User user){
        return new UserViewDTO(user.getFullName());
    }
}
