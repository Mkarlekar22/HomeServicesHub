package com.app.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "bookings")
public class Booking extends BaseEntity {

	 @Column(name = "booking_date",unique = false)
	 private LocalDate dateTime;
	 
	 @Column(name = "status", unique = false)
	 @Enumerated(EnumType.STRING)
	 private BookingStatus status;
	 
	 @Enumerated(EnumType.STRING)
	 @Column(name = "time_slot")
	 private TimeSlot timeSlot;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "Customer_id")
	 private User customer;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "service_provider_id")
	 private User serviceProvider;
	 
	 @OneToOne
	 @JoinColumn(name = "payment_id")
	 private Payment payment;
	 
	 
	 //a booking can have multiple services and each service can be 
	 //associated with multiple bookings
	 
	 @JsonIgnore
	 @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	        name = "booking_service",
	        joinColumns = @JoinColumn(name = "booking_id"), // Foreign key in join table for Booking
	        inverseJoinColumns = @JoinColumn(name = "service_id") // Foreign key in join table for Service
	    )
	    private List<Services> services;
	 

}
