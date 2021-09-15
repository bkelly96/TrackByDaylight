package com.Kelly.TrackByDaylight.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //Something for JPA to save to the Database
@Data //generates all of the getters and setters
public class Match {

    @Id //PrimaryKey
    @GeneratedValue //AutoIncrement ID

    private Long id;
    private String killer;
    private String map;
    private String perks;
    private int outcome;
    private boolean hatch;
}
