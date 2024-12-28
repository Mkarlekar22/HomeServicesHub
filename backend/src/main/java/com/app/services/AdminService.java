package com.app.services;

import java.util.List;

import com.app.dto.BookingDTO;
import com.app.dto.ServiceDTO;
import com.app.dto.UserDTO;
import com.app.exception.ResourceNotFoundException;

public interface AdminService {
    
    // Manage Services
    ServiceDTO addService(ServiceDTO serviceDTO);
    void deleteService(Long serviceId) throws ResourceNotFoundException;

    // Manage Users
    List<UserDTO> getAllUsers();
    void deleteUser(Long userId) throws ResourceNotFoundException;

    // Manage Bookings
    List<BookingDTO> getAllBookings();

    // View Customer Details
    UserDTO getCustomerById(Long customerId) throws ResourceNotFoundException;
}
