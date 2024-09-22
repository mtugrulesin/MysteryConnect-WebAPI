package com.example.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Document
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "fullName", nullable = false, length = 50)
    private String fullName;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 16)
    private String password;

    @Column(name= "stats")
    private UserStat stats;

    @Column(name="photoId")
    private String photoId;

    @Column(name="country")
    private String country;

    @Column(name="diamondCount")
    private int diamondCount;

    @Column(name = "gender")
    private String gender;

    @Column(name="premium")
    private boolean premium;

    @Column(name = "preDate")
    private String preDate;

    @Column(name= "age")
    private int age;
    @Column(name="photoList")
    private ArrayList photoList;

    @Column(name = "birthDay")
    private String birthDay;

//    public User(String fullName, String email,String birthDay,String password) {
//        this.fullName = fullName;
//        this.email = email;
//        this.birthDay = birthDay;
//        this.password = password;
//    }

    public User(String fullName, String email, String password, String photoId, String country, boolean premium, ArrayList photoList, String birthDay, String preDate, int age,UserStat stats,String gender, int diamondCount) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.photoId = photoId;
        this.country = country;
        this.premium = premium;
        this.photoList = photoList;
        this.birthDay = birthDay;
        this.preDate = preDate;
        this.age = age;
        this.stats = stats;
        this.gender = gender;
        this.diamondCount = diamondCount;
    }

}
