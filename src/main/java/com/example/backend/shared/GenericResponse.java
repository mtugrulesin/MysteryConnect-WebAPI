package com.example.backend.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public final class GenericResponse {

    private String message;
    private boolean success;

}
