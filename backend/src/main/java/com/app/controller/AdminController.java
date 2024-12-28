package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BookingDTO;
import com.app.dto.ServiceDTO;
import com.app.dto.UserDTO;
import com.app.exception.ResourceNotFoundException;
import com.app.services.AdminService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Add a new service
    @PostMapping("/services")
    public ResponseEntity<ServiceDTO> addService(@RequestBody ServiceDTO serviceDTO) {
        ServiceDTO addedService = adminService.addService(serviceDTO);
        return new ResponseEntity<>(addedService, HttpStatus.CREATED);
    }

    // Delete a service
    @DeleteMapping("/services/{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable Long serviceId) {
        try {
            adminService.deleteService(serviceId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // View all users
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = adminService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Delete a user
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        try {
            adminService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // View all bookings
    @GetMapping("/bookings")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = adminService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // View customer details by ID
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<UserDTO> getCustomerById(@PathVariable Long customerId) {
        try {
            UserDTO user = adminService.getCustomerById(customerId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
