package com.achievetrack.backend.service;

import com.achievetrack.backend.dto.AchievementRequest;
import com.achievetrack.backend.model.Achievement;
import com.achievetrack.backend.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AchievementService {

    @Autowired
    private AchievementRepository achievementRepository;

    public Achievement submitAchievement(String userId, String studentName, AchievementRequest request) {
        Achievement achievement = Achievement.builder()
                .studentId(userId)
                .studentName(studentName)
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .level(request.getLevel())
                .dateAchieved(LocalDateTime.parse(request.getDateAchieved() + "T00:00:00"))
                .proofUrls(request.getProofUrls())
                .roleInEvent(request.getRoleInEvent())
                .teamSize(request.getTeamSize())
                .technologiesUsed(request.getTechnologiesUsed())
                .skillsGained(request.getSkillsGained())
                .organizerName(request.getOrganizerName())
                .rank(request.getRank())
                .prize(request.getPrize())
                .eventLocation(request.getEventLocation())
                .impact(request.getImpact())
                .whatILearned(request.getWhatILearned())
                .status(Achievement.AchievementStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
                
        return achievementRepository.save(achievement);
    }

    public List<Achievement> getStudentAchievements(String studentId) {
        return achievementRepository.findByStudentId(studentId);
    }

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }
    
    public List<Achievement> getAchievementsByStatus(Achievement.AchievementStatus status) {
        return achievementRepository.findByStatus(status);
    }

    public Achievement updateAchievementStatus(String achievementId, Achievement.AchievementStatus status, String adminRemarks) {
        Achievement achievement = achievementRepository.findById(achievementId)
                .orElseThrow(() -> new RuntimeException("Achievement not found"));
                
        achievement.setStatus(status);
        achievement.setAdminRemarks(adminRemarks);
        return achievementRepository.save(achievement);
    }
}
