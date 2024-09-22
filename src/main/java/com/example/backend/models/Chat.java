package com.example.backend.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Document
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "members")
    private ArrayList members;

    @Column(name = "lastMessageTime")
    private Long lastMessageTime;

    @Column(
            name = "isRead"
    )
    private Map<String, Boolean> isRead;



    public Chat(ArrayList members, Map<String, Boolean> isRead) {
        this.members = members;
        this.lastMessageTime = 0L;
        this.isRead =  isRead;
    }


}
