package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.User;
import com.app.entities.UserRole;

public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findByRole(UserRole role);

	Optional<User> findByEmailIdAndPassword(String emailId, String password);

	Optional<User> findByEmailId(String emailId);

}
