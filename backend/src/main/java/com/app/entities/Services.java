package com.app.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@ToString
@Entity
@Table(name = "services")
public class Services extends BaseEntity {

	@Column(name = "service_name", length = 255, unique = false)
	private String serviceName;
	
	@Column(name = "description", length = 255, unique = false)
    private String description;
	
	@Enumerated(EnumType.STRING)
	private ServiceType serviceType;
	
	@Column(name = "price", unique = false)
	private double price;
	
	@ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
    private List<Booking> bookings = new ArrayList<>();
	

}
