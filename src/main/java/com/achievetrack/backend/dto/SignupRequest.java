package com.achievetrack.backend.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String fullName;
    private String email;
    private String password;
    private String studentId;
    private String department;
    private String role; // "STUDENT" or "ADMIN"
}
