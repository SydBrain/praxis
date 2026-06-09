package com.praxis.controller;

import com.praxis.DTO.ExperimentDTO;
import com.praxis.DTO.QuestionDTO;
import com.praxis.service.ExperimentService;
import com.praxis.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/experiments")
public class ExperimentController {

    private final ExperimentService experimentService;
    private final QuestionService questionService;

    public ExperimentController(
            ExperimentService experimentService,
            QuestionService questionService
    ) {

        this.experimentService = experimentService;
        this.questionService = questionService;
    }

    @GetMapping("/{experimentId}")
    public ExperimentDTO getExperimentById(@PathVariable Long experimentId) {
        return experimentService.findById(experimentId);
    }

    @GetMapping
    public List<ExperimentDTO> getAllExperiments() {
        return experimentService.findAll();
    }

    @GetMapping("/{experimentId}/questions")
    public List<QuestionDTO> getQuestionsByExperimentId(@PathVariable Long experimentId) {
        return questionService.findByExperimentId(experimentId);
    }
}
