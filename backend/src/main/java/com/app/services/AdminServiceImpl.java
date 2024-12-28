package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.BookingDTO;
import com.app.dto.ServiceDTO;
import com.app.dto.UserDTO;
import com.app.entities.Booking;
import com.app.entities.Services;
import com.app.entities.User;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.BookingRepository;
import com.app.repository.ServicesRepository;
import com.app.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ServicesRepository serviceRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Manage Services
    @Override
    public ServiceDTO addService(ServiceDTO serviceDTO) {
        Services service = modelMapper.map(serviceDTO, Services.class);
        Services savedService = serviceRepository.save(service);
        return modelMapper.map(savedService, ServiceDTO.class);
    }

    @Override
    public void deleteService(Long serviceId) throws ResourceNotFoundException {
        Services service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with ID: " + serviceId));
        serviceRepository.delete(service);
    }

    // Manage Users
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        userRepository.delete(user);
    }

    // Manage Bookings
    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
    }

    // View Customer Details
    @Override
    public UserDTO getCustomerById(Long customerId) throws ResourceNotFoundException {
        User user = userRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));
        return modelMapper.map(user, UserDTO.class);
    }
}
