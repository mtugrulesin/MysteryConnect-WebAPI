package com.example.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Document
public class UserStat {

    public UserStat(int messageCount, int photoPoints) {
        this.messageCount = messageCount;
        this.photoPoints = photoPoints;
    }

    @Column(name= "msgCount")
    private int messageCount;

    @Column(name= "photoPoints")
    private int photoPoints;


}
