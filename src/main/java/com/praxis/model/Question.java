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
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<QuestionOption> options = new ArrayList<>();
    private Integer sliderMin;
    private Integer sliderMax;
    private Integer sliderStep;
    @Column(columnDefinition = "TEXT")
    private String explanation;

    public Question(Experiment experiment, String text, String intuitiveAnswer, String correctAnswer, QuestionType questionType, String explanation) {
        this.experiment = experiment;
        this.text = text;
        this.intuitiveAnswer = intuitiveAnswer;
        this.correctAnswer = correctAnswer;
        this.questionType = questionType;
        this.explanation = explanation;
    }

    public Question(Experiment experiment, String text, String intuitiveAnswer, String correctAnswer, QuestionType questionType, Integer sliderMin, Integer sliderMax, Integer sliderStep, String explanation) {
        this.experiment = experiment;
        this.text = text;
        this.intuitiveAnswer = intuitiveAnswer;
        this.correctAnswer = correctAnswer;
        this.questionType = questionType;
        this.sliderMin = sliderMin;
        this.sliderMax = sliderMax;
        this.sliderStep = sliderStep;
        this.explanation = explanation;
    }

}
