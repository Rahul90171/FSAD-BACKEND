package com.achievetrack.backend.controller;

import com.achievetrack.backend.dto.PlatformFeedbackRequest;
import com.achievetrack.backend.model.PlatformFeedback;
import com.achievetrack.backend.repository.PlatformFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.achievetrack.backend.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/student/feedback")
public class PlatformFeedbackController {

    @Autowired
    private PlatformFeedbackRepository feedbackRepository;
    
    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity<?> submitFeedback(Authentication authentication, @RequestBody PlatformFeedbackRequest request) {
        String token = (String) authentication.getCredentials();
        String userId = tokenProvider.getUserIdFromToken(token);
        String fullName = tokenProvider.getFullNameFromToken(token);

        PlatformFeedback feedback = PlatformFeedback.builder()
                .studentId(userId)
                .studentName(fullName)
                .subject(request.getSubject())
                .message(request.getMessage())
                .build();

        PlatformFeedback saved = feedbackRepository.save(feedback);
        return ResponseEntity.ok(saved);
    }
}
