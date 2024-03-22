package com.example.restApi.models;

import com.example.restApi.repo.AddressRepo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	String state;
	String city;
	Integer zipcode;
	
	@OneToOne(mappedBy = "address",cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;
}
