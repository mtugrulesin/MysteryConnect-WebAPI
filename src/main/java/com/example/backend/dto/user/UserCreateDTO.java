package com.example.backend.dto.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserCreateDTO {
    private String fullName;
    private String email;
    private String birthDay;
    private String password;
    private String country;
    private String gender;
}
