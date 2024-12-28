package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Payments")
public class Payment extends BaseEntity {
	
	@Column(name = "amount", unique = false)
	private double amount;
	
	//@CreationTimestamp
	@Column(name = "payment_date", unique = false)
    private LocalDate paymentDate;
	
	@Column(name = "payment_method", length = 255, unique = false)
    private String paymentMethod;
	
	
}
