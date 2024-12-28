package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ServiceDTO;
import com.app.entities.ServiceType;
import com.app.exception.ResourceNotFoundException;
import com.app.services.CustomerServiceImpl;
import com.app.services.ServicesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/services")
public class ServicesController {

	@Autowired	
	private CustomerServiceImpl customerService;
	// Endpoint to get all services
	
	  @Autowired
	   private ServicesService servicesService;
	
	  
//	  Get services by category
	    @GetMapping("/services/{type}")
	    public ResponseEntity<List<ServiceDTO>> getServicesByCategory(@PathVariable ServiceType type) {
	        try {
	            List<ServiceDTO> services = servicesService.getServicesByCategory(type);
	            return new ResponseEntity<>(services, HttpStatus.OK);
	        } catch (ResourceNotFoundException e) {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }
	     }
	    
    @GetMapping("/services")
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        List<ServiceDTO> services = customerService.getAllServices();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    // Endpoint to search services
    @GetMapping("/services/search")
    public ResponseEntity<List<ServiceDTO>> searchServices(@RequestParam String keyword) {
        List<ServiceDTO> services = customerService.searchServices(keyword);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
}
