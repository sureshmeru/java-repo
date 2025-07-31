package com.primesoft.javatraining.ServiceLayer;

import com.primesoft.javatraining.DAO.UserProfileDAO;
import com.primesoft.javatraining.Model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserProfileDAO userProfileDAO;


    public void saveUser(UserProfile user) {
        userProfileDAO.save(user);
    }

    public UserProfile getUser(Long id) {
        return userProfileDAO.findById(id);
    }

    public List<UserProfile> getAllUsers() {
        return userProfileDAO.findAll();
    }

    public void deleteUser(Long id) {
        userProfileDAO.deleteById(id);
    }

    public UserProfile findByEmail(String email) {
        return userProfileDAO.findByEmail(email);
    }

    public UserProfile updateUserFields(Long id, UserProfile updates) {
        UserProfile existing = getUser(id);
        if (existing == null) return null;

        if (updates.getName() != null) existing.setName(updates.getName());
        if (updates.getSurname() != null) existing.setSurname(updates.getSurname());
        if (updates.getEmail() != null) existing.setEmail(updates.getEmail());
        if (updates.getPassword() != null && !updates.getPassword().isEmpty()) existing.setPassword(updates.getPassword());
        if (updates.getDateOfBirth() != null) existing.setDateOfBirth(updates.getDateOfBirth());
        if (updates.getMobile() != null) existing.setMobile(updates.getMobile());
        if (updates.getGender() != null) existing.setGender(updates.getGender());

        saveUser(existing);
        return existing;
    }

}
