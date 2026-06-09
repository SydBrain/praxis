package com.praxis.service;

import com.praxis.DTO.QuestionDTO;
import com.praxis.DTO.QuestionOptionDTO;
import com.praxis.exception.ResourceNotFoundException;
import com.praxis.repository.ExperimentRepository;
import com.praxis.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ExperimentRepository experimentRepository;

    public QuestionService(
            QuestionRepository questionRepository,
            ExperimentRepository experimentRepository
    ) {
        this.questionRepository = questionRepository;
        this.experimentRepository = experimentRepository;
    }

    public List<QuestionDTO> findByExperimentId(Long experimentId) {
        experimentRepository.findById(experimentId)
                .orElseThrow(() -> new ResourceNotFoundException("Experiment not found with id: " + experimentId));

        return questionRepository.findByExperimentId(experimentId)
                .stream()
                .map(question -> new QuestionDTO(
                        question.getId(),
                        question.getText(),
                        question.getIntuitiveAnswer(),
                        question.getCorrectAnswer(),
                        question.getQuestionType().name(),
                        question.getOptions().stream()
                                .map(opt -> new QuestionOptionDTO(opt.getId(), opt.getText()))
                                .toList(),
                        question.getSliderMin(),
                        question.getSliderMax(),
                        question.getSliderStep()
                )).toList();
    }
}
