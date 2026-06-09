package com.praxis.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionDTO {
    private Long id;
    private String text;
    private String intuitiveAnswer;
    private String correctAnswer;
    private String questionType;
    private List<QuestionOptionDTO> options;
    private Integer sliderMin;
    private Integer sliderMax;
    private Integer sliderStep;
    private String explanation;
}
