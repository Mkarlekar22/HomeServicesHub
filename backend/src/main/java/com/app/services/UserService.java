package com.app.services;

import java.util.List;

import com.app.dto.UserDTO;
import com.app.entities.UserRole;
import com.app.exception.ResourceNotFoundException;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO login(String emailId, String password);
    UserDTO getUserById(Long id) throws ResourceNotFoundException;
    UserDTO updateUser(Long id, UserDTO userDTO) throws ResourceNotFoundException;
    void deleteUser(Long id) throws ResourceNotFoundException;
    List<UserDTO> getAllUsers();
    List<UserDTO> getUsersByRole(UserRole role); // New method
	void forgetPassword(String emailId, String newPassword);
}
