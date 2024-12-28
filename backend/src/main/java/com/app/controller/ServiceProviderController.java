package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BookingDTO;
import com.app.dto.UserDTO;
import com.app.entities.UserRole;
import com.app.exception.ResourceNotFoundException;
import com.app.services.ServiceProviderServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/service-provider")
public class ServiceProviderController {

    @Autowired
    private ServiceProviderServiceImpl serviceProviderService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getServiceProviderById(@PathVariable Long userId) throws ResourceNotFoundException {
        UserDTO userDTO = serviceProviderService.getServiceProviderById(userId);
        
        if (userDTO.getRole() != UserRole.ROLE_SERVICE_PROVIDER) {
            throw new ResourceNotFoundException("User with ID: " + userId + " is not a service provider.");
        }

        return ResponseEntity.ok(userDTO);
    }


    // Get all bookings for a Service Provider
    //@PreAuthorize("hasRole('SERVICE_PROVIDER')")
    @GetMapping("/{userId}/bookings")
    public ResponseEntity<List<BookingDTO>> getAllBookings(@PathVariable Long userId) throws ResourceNotFoundException {
        List<BookingDTO> bookings = serviceProviderService.getAllBookings(userId);
        return ResponseEntity.ok(bookings);
    }

 // Get booking status by ID
    //@PreAuthorize("hasRole('SERVICE_PROVIDER')")
    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingStatus(@PathVariable Long bookingId) throws ResourceNotFoundException {
        BookingDTO bookingDTO = serviceProviderService.getBookingStatus(bookingId);
        return ResponseEntity.ok(bookingDTO);
    }

    // Update Service Provider profile
    //@PreAuthorize("hasRole('SERVICE_PROVIDER')")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateProfile(@PathVariable Long userId, @RequestBody @Valid UserDTO userDTO) throws ResourceNotFoundException {
        UserDTO updatedUserDTO = serviceProviderService.updateServiceProvider(userId, userDTO);
        return ResponseEntity.ok(updatedUserDTO);
    }
}