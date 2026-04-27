package com.achievetrack.backend.dto;

import lombok.Data;

@Data
public class PlatformFeedbackRequest {
    private String subject;
    private String message;
}
