package com.praxis.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionResultDTO {
    private Long questionId;
    private String questionText;
    private String userAnswer;
    private String correctAnswer;
    boolean isCorrect;
    Map<String, Long> answerDistribution;
}
