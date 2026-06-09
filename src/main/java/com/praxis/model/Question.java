package com.praxis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @Column(columnDefinition = "TEXT")
    private String text;
    private String intuitiveAnswer;
    private String correctAnswer;
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionOption> options = new ArrayList<>();
    private Integer sliderMin;
    private Integer sliderMax;
    private Integer sliderStep;

    public Question(Experiment experiment, String text, String intuitiveAnswer, String correctAnswer, QuestionType questionType) {
        this.experiment = experiment;
        this.text = text;
        this.intuitiveAnswer = intuitiveAnswer;
        this.correctAnswer = correctAnswer;
        this.questionType = questionType;
    }

    public Question(Experiment experiment, String text, String intuitiveAnswer, String correctAnswer, QuestionType questionType, Integer sliderMin, Integer sliderMax, Integer sliderStep) {
        this(experiment, text, intuitiveAnswer, correctAnswer, questionType);
        this.sliderMin = sliderMin;
        this.sliderMax = sliderMax;
        this.sliderStep = sliderStep;
    }
}
