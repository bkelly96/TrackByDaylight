package com.Kelly.TrackByDaylight.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity //Something for JPA to save to the Database
@Data //generates all of the getters and setters
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id //PrimaryKey
    @GeneratedValue //AutoIncrement ID

    private Long id;
    private String killer;
    private String map;
    private String perks;
    private int outcome;
    private boolean hatch;
    private Instant createdAt;
}
