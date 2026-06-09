package com.praxis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Experiment {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String description;
    private boolean hasScore;

    public Experiment(String name, String description, boolean hasScore) {
        this.name = name;
        this.description = description;
        this.hasScore = hasScore;
    }

}
