package com.praxis.controller;

import com.praxis.DTO.CreateSessionRequest;
import com.praxis.DTO.SessionResultDTO;
import com.praxis.DTO.UserSessionDTO;
import com.praxis.service.SessionService;
import com.praxis.service.UserSessionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final UserSessionService userSessionService;
    private final SessionService sessionService;

    public SessionController(
            UserSessionService userSessionService,
            SessionService sessionService
    ) {
        this.userSessionService = userSessionService;
        this.sessionService = sessionService;
    }

    @PostMapping
    public UserSessionDTO createNewSession(@RequestBody CreateSessionRequest request) {
        return userSessionService.createSession(request.getExperimentId());
    }

    @GetMapping("/{sessionId}/results")
    public SessionResultDTO getSessionResults(@PathVariable Long sessionId) {
        return sessionService.getSessionResults(sessionId);
    }
}
