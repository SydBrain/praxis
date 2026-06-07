package com.praxis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    @JoinColumn(name = "experiment_id")
    private Experiment experiment;
    private String text;
    private String intuitiveAnswer;
    private String correctAnswer;

    public Question(Experiment experiment, String text, String intuitiveAnswer, String correctAnswer) {
        this.experiment = experiment;
        this.text = text;
        this.intuitiveAnswer = intuitiveAnswer;
        this.correctAnswer = correctAnswer;
    }
}
