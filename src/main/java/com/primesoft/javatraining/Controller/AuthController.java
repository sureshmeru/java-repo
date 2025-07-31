package com.primesoft.javatraining.Controller;

import com.primesoft.javatraining.Model.LoginRequest;
import com.primesoft.javatraining.Model.LoginResponse;
import com.primesoft.javatraining.Model.UserProfile;
import com.primesoft.javatraining.ServiceLayer.AuthService;
import com.primesoft.javatraining.ServiceLayer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        
        if (response.getToken() != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody UserProfile userProfile) {
        try {
            // Check if user already exists
            UserProfile existingUser = userService.findByEmail(userProfile.getEmail());
            if (existingUser != null) {
                return ResponseEntity.badRequest()
                    .body(new LoginResponse(null, null, null, "User already exists"));
            }

            // Encode password
            userProfile.setPassword(passwordEncoder.encode(userProfile.getPassword()));
            
            // Save user
            userService.saveUser(userProfile);

            // Generate token for new user
            LoginRequest loginRequest = new LoginRequest(userProfile.getEmail(), userProfile.getPassword());
            LoginResponse response = authService.login(loginRequest);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new LoginResponse(null, null, null, "Registration failed: " + e.getMessage()));
        }
    }
} 