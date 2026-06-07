package com.praxis.service;

import com.praxis.DTO.QuestionDTO;
import com.praxis.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<QuestionDTO> findByExperimentId(Long experimentId) {
        return this.questionRepository.findByExperimentId(experimentId)
                .stream()
                .map(question -> new QuestionDTO(
                        question.getId(),
                        question.getText(),
                        question.getIntuitiveAnswer(),
                        question.getCorrectAnswer()
                )).toList();
    }
}
