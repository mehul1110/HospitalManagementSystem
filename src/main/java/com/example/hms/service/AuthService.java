package com.example.hms.service;

import com.example.hms.config.JwtService;
import com.example.hms.dto.AuthRequest;
import com.example.hms.dto.AuthResponse;
import com.example.hms.entity.Role;
import com.example.hms.entity.User;
import com.example.hms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse registerUser(AuthRequest request) {
        if(repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        var user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ADMIN);
        
        repository.save(user);
        return new AuthResponse(null, "User registered successfully");
    }

    public AuthResponse loginUser(AuthRequest request) {
        authenticate(request);
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken, "Login successful");
    }

    public void authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
    }
}
