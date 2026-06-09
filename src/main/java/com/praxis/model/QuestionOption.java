package com.praxis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public QuestionOption(String text, Question question) {
        this.text = text;
        this.question = question;
    }
}