package com.praxis.controller;

import com.praxis.DTO.CreateSessionRequest;
import com.praxis.DTO.UserSessionDTO;
import com.praxis.service.UserSessionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final UserSessionService userSessionService;

    public SessionController(
            UserSessionService userSessionService
    ) {
        this.userSessionService = userSessionService;
    }

    @PostMapping
    public UserSessionDTO createNewSession(@RequestBody CreateSessionRequest request) {
        return userSessionService.createSession(request.getExperimentId());
    }
}
