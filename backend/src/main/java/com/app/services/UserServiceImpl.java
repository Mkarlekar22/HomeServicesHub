package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.UserDTO;
import com.app.entities.User;
import com.app.entities.UserRole;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        // Validate serviceType based on role
        if (userDTO.getRole() == UserRole.ROLE_SERVICE_PROVIDER && userDTO.getServiceType() == null) {
            throw new IllegalArgumentException("Service Type is required for Service Providers");
        }

        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }
    
    //USER LOGIN
    public UserDTO login(String emailId, String password) throws ResourceNotFoundException {
        User user = userRepository.findByEmailIdAndPassword(emailId, password)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));

        return modelMapper.map(user, UserDTO.class); // Assuming you have a ModelMapper bean configured
    }
    
    //FORGOT PASSWORD
    public void forgetPassword(String emailId, String newPassword) throws ResourceNotFoundException {
        User user = userRepository.findByEmailId(emailId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + emailId));

        user.setPassword(newPassword);
        userRepository.save(user);
    }


    @Override
    public UserDTO getUserById(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        modelMapper.map(userDTO, user);

        // Validate serviceType based on role
        if (userDTO.getRole() == UserRole.ROLE_SERVICE_PROVIDER && userDTO.getServiceType() == null) {
            throw new IllegalArgumentException("Service Type is required for Service Providers");
        }

        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        userRepository.delete(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getUsersByRole(UserRole role) {
        List<User> users = userRepository.findByRole(role);
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
    
    
}
