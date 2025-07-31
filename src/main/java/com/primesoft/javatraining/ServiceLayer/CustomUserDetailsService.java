package com.primesoft.javatraining.ServiceLayer;

import com.primesoft.javatraining.DAO.UserProfileDAO;
import com.primesoft.javatraining.Model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserProfile userProfile = userProfileDAO.findByEmail(email);
        
        if (userProfile == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new User(
            userProfile.getEmail(),
            userProfile.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("USER"))
        );
    }
} 