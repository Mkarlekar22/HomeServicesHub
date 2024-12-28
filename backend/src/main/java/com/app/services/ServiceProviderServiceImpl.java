package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.BookingDTO;
import com.app.dto.UserDTO;
import com.app.entities.Booking;
import com.app.entities.User;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.BookingRepository;
import com.app.repository.PaymentRepository;
import com.app.repository.UserRepository;

@Service
public class ServiceProviderServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Get Service Provider by ID
    public UserDTO getServiceProviderById(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Service provider not found with ID: " + userId));
        return modelMapper.map(user, UserDTO.class);
    }


    // Get all bookings for a Service Provider
    public List<BookingDTO> getAllBookings(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Service provider not found with ID: " + userId));

        List<Booking> bookings = bookingRepository.findByServiceProvider(user); // Fixed method
        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
    }

    // Get booking status by ID
    public BookingDTO getBookingStatus(Long bookingId) throws ResourceNotFoundException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + bookingId));

        return modelMapper.map(booking, BookingDTO.class);
    }

    // Update Service Provider profile
    public UserDTO updateServiceProvider(Long userId, UserDTO userDTO) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Service provider not found with ID: " + userId));

        // Map the updated fields from DTO to entity
        modelMapper.map(userDTO, user);

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDTO.class);
    }
}
