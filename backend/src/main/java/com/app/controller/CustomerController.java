package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDTO;
import com.app.entities.UserRole;
import com.app.exception.ResourceNotFoundException;
import com.app.services.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private UserService userService;

    // Get all customers
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(userService.getUsersByRole(UserRole.ROLE_CUSTOMER));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customerId) throws ResourceNotFoundException {
        UserDTO customerDto = userService.getUserById(customerId);
        
        if (customerDto == null) {
            throw new ResourceNotFoundException("Customer not found with ID: " + customerId);
        }

        if (customerDto.getRole() != UserRole.ROLE_CUSTOMER) {
            throw new ResourceNotFoundException("User with ID: " + customerId + " is not a customer.");
        }

        return ResponseEntity.ok(customerDto);
    }


    // Update customer details
    //@PreAuthorize("hasRole('ROLE_ADMIN') or @securityService.hasCustomer(#customerId)")
    @PutMapping("/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customerId, @RequestBody @Valid UserDTO customerDto) throws ResourceNotFoundException {
        UserDTO updatedCustomer = userService.updateUser(customerId, customerDto);
        if (updatedCustomer == null) {
            throw new ResourceNotFoundException("Customer not found with ID: " + customerId);
        }
        return ResponseEntity.ok(updatedCustomer);
    }

 // Delete customer
    //@PreAuthorize("hasRole('ROLE_ADMIN') or @securityService.hasCustomer(#customerId)")
    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) throws ResourceNotFoundException {
        try {
            userService.deleteUser(customerId); // Just perform deletion
            return ResponseEntity.ok().build(); // No content to return after deletion
        } catch (Exception e) {
            throw new ResourceNotFoundException("Customer not found with ID: " + customerId);
        }
    }
}
