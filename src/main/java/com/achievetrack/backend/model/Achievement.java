package com.achievetrack.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "achievements")
public class Achievement {
    @Id
    private String id;
    private String studentId; // Reference to User.id
    private String studentName; // Denormalized for easy queries
    private String title;
    private String description;
    private String category; // e.g., Hackathon, Sports, Cultural, Research
    private String level; // e.g., State, National, International
    private LocalDateTime dateAchieved;
    private List<String> proofUrls; // S3 or other storage links
    
    // New Detailed Fields
    private String roleInEvent;
    private String teamSize;
    private List<String> technologiesUsed;
    private List<String> skillsGained;
    private String organizerName;
    private String rank;
    private String prize;
    private String eventLocation;
    private String impact;
    private String whatILearned;
    
    @Builder.Default
    private AchievementStatus status = AchievementStatus.PENDING;
    
    private String adminRemarks;
    
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public enum AchievementStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
}
