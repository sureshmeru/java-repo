package com.primesoft.javatraining.DAO;

import com.primesoft.javatraining.Model.UserProfile;
import java.util.List;

public interface UserProfileDAO {
    void save(UserProfile userProfile);
    UserProfile findById(Long id);
    List<UserProfile> findAll();
    void deleteById(Long id);
    UserProfile findByEmail(String email);
}
