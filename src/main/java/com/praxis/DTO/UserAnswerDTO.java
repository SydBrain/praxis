package com.praxis.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAnswerDTO {
    private Long id;
    private Long sessionId;
    private Long questionId;
    private String answerGiven;
    private boolean isCorrect;
}
