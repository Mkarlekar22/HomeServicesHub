package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
