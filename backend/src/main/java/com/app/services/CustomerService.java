package com.app.services;

import java.util.List;

import com.app.dto.ServiceDTO;
import com.app.dto.UserDTO;
import com.app.exception.ResourceNotFoundException;

public interface CustomerService {

    List<ServiceDTO> getAllServices();

    List<ServiceDTO> searchServices(String keyword);

    UserDTO getCustomerById(Long id) throws ResourceNotFoundException;

    UserDTO updateCustomer(Long id, UserDTO userDTO) throws ResourceNotFoundException;

    void deleteCustomer(Long id) throws ResourceNotFoundException;
}
