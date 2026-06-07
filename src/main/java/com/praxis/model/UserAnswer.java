package com.praxis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_session_id")
    private UserSession userSession;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    private String answerGiven;
    private boolean isCorrect;

}
