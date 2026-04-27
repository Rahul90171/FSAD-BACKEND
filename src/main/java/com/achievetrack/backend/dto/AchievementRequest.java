package com.achievetrack.backend.dto;

import lombok.Data;

@Data
public class AchievementRequest {
    private String title;
    private String description;
    private String category;
    private String level;
    private String dateAchieved;
    private java.util.List<String> proofUrls;
    
    private String roleInEvent;
    private String teamSize;
    private java.util.List<String> technologiesUsed;
    private java.util.List<String> skillsGained;
    private String organizerName;
    private String rank;
    private String prize;
    private String eventLocation;
    private String impact;
    private String whatILearned;
}
