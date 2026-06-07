package com.praxis.controller;

import com.praxis.DTO.SubmitAnswersRequest;
import com.praxis.DTO.UserAnswerDTO;
import com.praxis.service.UserAnswerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final UserAnswerService userAnswerService;

    public AnswerController(
            UserAnswerService userAnswerService
    ) {
        this.userAnswerService = userAnswerService;
    }

    @PostMapping
    public List<UserAnswerDTO> sendUserAnswers(@RequestBody SubmitAnswersRequest answers) {
        return userAnswerService.submitAnswers(answers);
    }
}
