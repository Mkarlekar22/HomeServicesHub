package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDTO;
import com.app.entities.UserRole;
import com.app.exception.ResourceNotFoundException;
import com.app.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO) {
        // If the role is SERVICE_PROVIDER, ensure serviceType is provided
        if (userDTO.getRole() == UserRole.ROLE_SERVICE_PROVIDER && userDTO.getServiceType() == null) {
            return ResponseEntity.badRequest().body(null); // Or throw an exception
        }

        UserDTO createdUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }
    
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginData) {
        String emailId = loginData.get("emailId");
        String password = loginData.get("password");

        try {
            UserDTO userDTO = userService.login(emailId, password);
            
            // You can create a session or generate a token here if needed
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            //response.put("token", "dummy-token"); // Replace this with actual token if you plan to implement later

            return ResponseEntity.ok(response);

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));
        }
    }

    // Get all users
    @GetMapping("/get-all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) throws ResourceNotFoundException {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    // Update user by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) throws ResourceNotFoundException {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws ResourceNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Get users by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserDTO>> getUsersByRole(@PathVariable String role) {
        UserRole userRole = UserRole.valueOf(role.toUpperCase());
        List<UserDTO> users = userService.getUsersByRole(userRole);
        return ResponseEntity.ok(users);
    }
}
