package com.achievetrack.backend.controller;

import com.achievetrack.backend.dto.AchievementRequest;
import com.achievetrack.backend.dto.ApiResponse;
import com.achievetrack.backend.model.Achievement;
import com.achievetrack.backend.security.CustomUserDetails;
import com.achievetrack.backend.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private AchievementService achievementService;

    @PostMapping("/achievements")
    public ResponseEntity<?> submitAchievement(@AuthenticationPrincipal CustomUserDetails userDetails,
                                               @RequestBody AchievementRequest request) {
        try {
            Achievement achievement = achievementService.submitAchievement(userDetails.getId(), userDetails.getFullName(), request);
            return ResponseEntity.ok(achievement);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/achievements")
    public ResponseEntity<List<Achievement>> getMyAchievements(@AuthenticationPrincipal CustomUserDetails userDetails) {
        List<Achievement> achievements = achievementService.getStudentAchievements(userDetails.getId());
        return ResponseEntity.ok(achievements);
    }
}
