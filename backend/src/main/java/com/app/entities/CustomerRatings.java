package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "customerratings")
public class CustomerRatings extends BaseEntity{

	@Column(name = "rating", unique = false)
	 private int rating; //rating out of 5
	
	@Column(name = "coomments", length = 255, unique = false)
	 private String comment;
	
	@ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
	
	
	@ManyToOne
    @JoinColumn(name = "service_provider_id")
    private User serviceProvider;
	
	
	
	

}
