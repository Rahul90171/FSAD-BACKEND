package com.achievetrack.backend.controller;

import com.achievetrack.backend.dto.ApiResponse;
import com.achievetrack.backend.model.Achievement;
import com.achievetrack.backend.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AchievementService achievementService;

    @GetMapping("/achievements")
    public ResponseEntity<List<Achievement>> getAllAchievements(@RequestParam(required = false) String status) {
        if (status != null && !status.isEmpty()) {
            return ResponseEntity.ok(achievementService.getAchievementsByStatus(Achievement.AchievementStatus.valueOf(status.toUpperCase())));
        }
        return ResponseEntity.ok(achievementService.getAllAchievements());
    }

    @PutMapping("/achievements/{id}/status")
    public ResponseEntity<?> updateAchievementStatus(@PathVariable String id, @RequestBody Map<String, String> request) {
        try {
            Achievement.AchievementStatus status = Achievement.AchievementStatus.valueOf(request.get("status").toUpperCase());
            String remarks = request.get("remarks");
            
            Achievement achievement = achievementService.updateAchievementStatus(id, status, remarks);
            return ResponseEntity.ok(achievement);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
}
