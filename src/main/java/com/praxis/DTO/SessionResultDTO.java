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
public class SessionResultDTO {
    private int score;
    private int total;
    private List<QuestionResultDTO> questions;
    private boolean hasScore;
}
