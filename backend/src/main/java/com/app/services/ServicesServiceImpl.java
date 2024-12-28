package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ServiceDTO;
import com.app.entities.ServiceType;
import com.app.entities.Services;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.ServicesRepository;

@Service
public class ServicesServiceImpl implements ServicesService {

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
    public List<ServiceDTO> getServicesByCategory(ServiceType category) throws ResourceNotFoundException {
        List<Services> services = servicesRepository.findByServiceType(category);
        if (services.isEmpty()) {
            throw new ResourceNotFoundException("No services found for category: " + category);
        }
        return services.stream()
                .map(service -> modelMapper.map(service, ServiceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ServiceDTO getServiceById(Long serviceId) throws ResourceNotFoundException {
        Services service = servicesRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with ID: " + serviceId));
        return modelMapper.map(service, ServiceDTO.class);
    }
}
