package com.example.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name ="adverts")
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId",nullable = false,length = 50)
    private Long userId;

    @Column(name = "description",nullable = false,length = 450)
    private String description;

    @Column(name = "category",nullable = false,length = 50)
    private String category;

    public Advert(Long userId, String description, String category) {
        this.userId = userId;
        this.description = description;
        this.category = category;
    }
}
