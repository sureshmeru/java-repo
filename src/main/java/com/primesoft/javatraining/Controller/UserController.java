package com.primesoft.javatraining.Controller;

import com.primesoft.javatraining.Model.UserProfile;
import com.primesoft.javatraining.ServiceLayer.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserProfile userProfile) {
        // Encode password before saving
        userProfile.setPassword(passwordEncoder.encode(userProfile.getPassword()));
        userService.saveUser(userProfile);
        return ResponseEntity.ok("User profile created successfully!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserById(@PathVariable Long id) {
        UserProfile user = userService.getUser(id);
        if (user != null) {
            // Don't return password in response
            user.setPassword(null);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserProfile>> getAllUsers() {
        List<UserProfile> users = userService.getAllUsers();
        // Remove passwords from response
        users.forEach(user -> user.setPassword(null));
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody @Valid UserProfile userProfile) {
        UserProfile existingUser = userService.getUser(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        // Only encode password if it's being changed
        if (userProfile.getPassword() != null && !userProfile.getPassword().isEmpty()) {
            userProfile.setPassword(passwordEncoder.encode(userProfile.getPassword()));
        } else {
            userProfile.setPassword(null); // So it won't overwrite with null
        }

        userService.updateUserFields(id, userProfile);
        return ResponseEntity.ok("User updated successfully!");
    }
}
