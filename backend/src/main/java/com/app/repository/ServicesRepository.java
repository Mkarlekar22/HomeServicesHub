package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.ServiceType;
import com.app.entities.Services;

public interface ServicesRepository extends JpaRepository<Services, Long> {

	List<Services> findByServiceType(ServiceType category);

	List<Services> findByServiceNameContainingIgnoreCase(String keyword);

}
