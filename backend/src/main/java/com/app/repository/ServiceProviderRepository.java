package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.User;

public interface ServiceProviderRepository extends JpaRepository<User, Long> {

	User findByEmailId(String emailId);

	boolean existsByEmailId(String emailId);



	

}
