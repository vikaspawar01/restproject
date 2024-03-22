package com.example.restApi.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank
	@NotNull(message = "Name is Required")
	@Size(max = 20, message = "max 20 characters are allowed")
	@Pattern(regexp = "[a-zA-Z]+",message = "Only Characters are Allow")
	private String name;
	

	@NotNull(message = "Email is required")
	@Email(message = "please enter valid email address")
	private String email;
	
	@NotBlank(message = "password must not be blank")
	@Size(min = 6,max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]+",message = "Only AlphaNumeric Characters Allow")
	private String password;
	
	@CreatedDate
	@Column(nullable = false,updatable = false)
	
	private LocalDateTime createDate;
	
	@LastModifiedDate
	@Column(insertable = false)
	private LocalDateTime lastModified;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
	private List<Blog> blog;
}
