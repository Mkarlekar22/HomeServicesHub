package com.app.services;

import java.util.List;

import com.app.dto.ServiceDTO;
import com.app.entities.ServiceType;
import com.app.exception.ResourceNotFoundException;

public interface ServicesService {

    // Get all services
    List<ServiceDTO> getAllServices();

    // Get services by category
    List<ServiceDTO> getServicesByCategory(ServiceType category) throws ResourceNotFoundException;

    // Get a specific service by ID
    ServiceDTO getServiceById(Long serviceId) throws ResourceNotFoundException;
}
