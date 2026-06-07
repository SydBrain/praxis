package com.praxis.service;

import com.praxis.DTO.UserSessionDTO;
import com.praxis.model.Experiment;
import com.praxis.model.UserSession;
import com.praxis.repository.ExperimentRepository;
import com.praxis.repository.UserSessionRepository;
import org.springframework.stereotype.Service;

@Service
public class UserSessionService {

    private final UserSessionRepository userSessionRepository;
    private final ExperimentRepository experimentRepository;

    public UserSessionService(
            UserSessionRepository userSessionRepository,
            ExperimentRepository experimentRepository
    ) {
        this.userSessionRepository = userSessionRepository;
        this.experimentRepository = experimentRepository;
    }

    public UserSessionDTO createSession(Long experimentId) {
        Experiment experiment = experimentRepository.findById(experimentId)
                .orElseThrow(() -> new RuntimeException("Experiment not found"));

        UserSession userSession = new UserSession(experiment);
        userSessionRepository.save(userSession);

        return new UserSessionDTO(
                userSession.getId(),
                userSession.getExperiment().getId(),
                userSession.getCreatedAt()
        );
    }
}
