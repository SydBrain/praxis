package com.praxis.service;

import com.praxis.DTO.QuestionResultDTO;
import com.praxis.DTO.SessionResultDTO;
import com.praxis.exception.ResourceNotFoundException;
import com.praxis.model.Question;
import com.praxis.model.UserAnswer;
import com.praxis.model.UserSession;
import com.praxis.repository.QuestionRepository;
import com.praxis.repository.UserAnswerRepository;
import com.praxis.repository.UserSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SessionService {

    private final UserSessionRepository userSessionRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final QuestionRepository questionRepository;

    public SessionService(
            UserSessionRepository userSessionRepository,
            UserAnswerRepository userAnswerRepository,
            QuestionRepository questionRepository
    ) {
        this.userSessionRepository = userSessionRepository;
        this.userAnswerRepository = userAnswerRepository;
        this.questionRepository = questionRepository;
    }

    public SessionResultDTO getSessionResults(Long sessionId) {
        UserSession userSession = userSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("User session not found with id: " + sessionId));

        List<UserAnswer> userAnswers = userAnswerRepository.findByUserSession_Id(sessionId);

        List<QuestionResultDTO> questionResults = userAnswers.stream()
                .map(userAnswer -> {
                    Map<String, Long> distribution = userAnswerRepository
                            .findByQuestion_Id(userAnswer.getQuestion().getId())
                            .stream()
                            .collect(Collectors.groupingBy(UserAnswer::getAnswerGiven, Collectors.counting()));

                    return new QuestionResultDTO(
                            userAnswer.getQuestion().getId(),
                            userAnswer.getQuestion().getText(),
                            userAnswer.getAnswerGiven(),
                            userAnswer.getQuestion().getCorrectAnswer(),
                            userAnswer.isCorrect(),
                            distribution,
                            userAnswer.getQuestion().getExplanation()
                    );
                })
                .toList();

        int score = (int) userAnswers.stream().filter(UserAnswer::isCorrect).count();

        return new SessionResultDTO(score, userAnswers.size(), questionResults, userSession.getExperiment().isHasScore());
    }
}
