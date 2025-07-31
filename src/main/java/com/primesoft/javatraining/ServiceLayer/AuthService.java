package com.primesoft.javatraining.ServiceLayer;

import com.primesoft.javatraining.Model.LoginRequest;
import com.primesoft.javatraining.Model.LoginResponse;
import com.primesoft.javatraining.Model.UserProfile;
import com.primesoft.javatraining.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);

            UserProfile userProfile = userService.findByEmail(loginRequest.getEmail());
            
            return new LoginResponse(
                token,
                userProfile.getEmail(),
                userProfile.getName(),
                "Login successful"
            );
        } catch (Exception e) {
            return new LoginResponse(
                null,
                null,
                null,
                "Invalid credentials"
            );
        }
    }
} 