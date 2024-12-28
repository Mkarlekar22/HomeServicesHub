package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ServiceDTO;
import com.app.dto.UserDTO;
import com.app.entities.Services;
import com.app.entities.User;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.ServicesRepository;
import com.app.repository.UserRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ServiceDTO> getAllServices() {
        List<Services> services = servicesRepository.findAll();
        return services.stream()
                .map(service -> modelMapper.map(service, ServiceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDTO> searchServices(String keyword) {
        List<Services> services = servicesRepository.findByServiceNameContainingIgnoreCase(keyword);
        return services.stream()
                .map(service -> modelMapper.map(service, ServiceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getCustomerById(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateCustomer(Long id, UserDTO userDTO) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
        modelMapper.map(userDTO, user);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteCustomer(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
        userRepository.delete(user);
    }
}
