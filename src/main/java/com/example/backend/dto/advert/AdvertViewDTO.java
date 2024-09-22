package com.example.backend.dto.advert;


import com.example.backend.models.Advert;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class AdvertViewDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long userId;

    private final String description;

    private final String category;

    private AdvertViewDTO(Long userId,String description,String category){
        this.userId = userId;
        this.description = description;
        this.category = category;

    }

    public static AdvertViewDTO of(Advert advert){
        return new AdvertViewDTO(advert.getUserId(),advert.getDescription(),advert.getCategory());
    }



}
