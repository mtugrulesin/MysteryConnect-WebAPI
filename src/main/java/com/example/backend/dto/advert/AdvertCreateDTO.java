package com.example.backend.dto.advert;

import lombok.Data;


@Data
public class AdvertCreateDTO {


    private Long userId;
    private String description;
    private String category;

}
