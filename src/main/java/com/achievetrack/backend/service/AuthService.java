package com.achievetrack.backend.service;

import com.achievetrack.backend.dto.LoginRequest;
import com.achievetrack.backend.dto.SignupRequest;
import com.achievetrack.backend.dto.JwtAuthResponse;
import com.achievetrack.backend.model.Role;
import com.achievetrack.backend.model.User;
import com.achievetrack.backend.repository.UserRepository;
import com.achievetrack.backend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public JwtAuthResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        String role = user.getRoles().iterator().next().name();

        return new JwtAuthResponse(jwt, role, user.getFullName());
    }

    public User registerUser(SignupRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email is already taken!");
        }

        if(signUpRequest.getRole().equals("STUDENT") && userRepository.existsByStudentId(signUpRequest.getStudentId())) {
            throw new RuntimeException("Student ID is already in use!");
        }

        Role userRole = signUpRequest.getRole().equals("ADMIN") ? Role.ROLE_ADMIN : Role.ROLE_STUDENT;

        User user = User.builder()
                .fullName(signUpRequest.getFullName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles(Collections.singleton(userRole))
                .build();
                
        if (userRole == Role.ROLE_STUDENT) {
            user.setStudentId(signUpRequest.getStudentId());
            user.setDepartment(signUpRequest.getDepartment());
        }

        return userRepository.save(user);
    }
}
