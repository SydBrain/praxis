package com.praxis.service;

import com.praxis.DTO.AnswerRequest;
import com.praxis.DTO.SubmitAnswersRequest;
import com.praxis.DTO.UserAnswerDTO;
import com.praxis.exception.ResourceNotFoundException;
import com.praxis.model.Question;
import com.praxis.model.UserAnswer;
import com.praxis.model.UserSession;
import com.praxis.repository.QuestionRepository;
import com.praxis.repository.UserAnswerRepository;
import com.praxis.repository.UserSessionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;
    private final UserSessionRepository userSessionRepository;
    private final QuestionRepository questionRepository;

    public UserAnswerService(
            UserAnswerRepository userAnswerRepository,
            UserSessionRepository userSessionRepository,
            QuestionRepository questionRepository
    ) {
        this.userAnswerRepository = userAnswerRepository;
        this.userSessionRepository = userSessionRepository;
        this.questionRepository = questionRepository;
    }

    public List<UserAnswerDTO> submitAnswers(SubmitAnswersRequest answers) {

        List<UserAnswerDTO> result = new ArrayList<>();

        UserSession session = userSessionRepository.findById(answers.getSessionId())
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + answers.getSessionId()));

        for (AnswerRequest answer: answers.getUserAnswers()) {
            boolean isCorrect;

            Question currentQuestion = questionRepository.findById(answer.getQuestionId())
                    .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + answer.getQuestionId()));

            isCorrect = currentQuestion.getCorrectAnswer() != null &&
                    answer.getAnswerGiven().trim().equals(currentQuestion.getCorrectAnswer().trim());

            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setUserSession(session);
            userAnswer.setQuestion(currentQuestion);
            userAnswer.setAnswerGiven(answer.getAnswerGiven());
            userAnswer.setCorrect(isCorrect);

            UserAnswer saved = userAnswerRepository.save(userAnswer);
            result.add(new UserAnswerDTO(
                    saved.getId(),
                    saved.getUserSession().getId(),
                    saved.getQuestion().getId(),
                    saved.getAnswerGiven(),
                    saved.isCorrect()
            ));
        }

        return result;
    }

}
