package com.achievetrack.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String role;
    private String fullName;
    
    public JwtAuthResponse(String accessToken, String role, String fullName) {
        this.accessToken = accessToken;
        this.role = role;
        this.fullName = fullName;
    }
}
