package com.example.restApi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails firstuser=User
				.withUsername("vikas")
				.password(passwordEncoder().encode("1122aabb"))
				.roles("ADMIN","USER")
				.build();
		
		UserDetails seconduser=User
				.withUsername("mayur")
				.password(passwordEncoder().encode("1122aabb"))
				.roles("USER")
				.build();
		
		UserDetails thirduser=User
				.withUsername("rohit")
				.password(passwordEncoder().encode("1122aabb"))
				.roles("USER")
				.build();
		
		UserDetails fourthuser=User
				.withUsername("prakash")
				.password(passwordEncoder().encode("1122aabb"))
				.roles("USER")
				.build();
		
		System.out.println(passwordEncoder().encode("1122aabb"));
		
		InMemoryUserDetailsManager detailsManager=new InMemoryUserDetailsManager(firstuser,seconduser,thirduser,fourthuser);
		return detailsManager;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(authorize->
		authorize.requestMatchers(HttpMethod.DELETE,"/users/**").hasRole("ADMIN")
		.requestMatchers(HttpMethod.GET,"/users").permitAll()
		.requestMatchers(HttpMethod.GET,"/users/**").hasAnyRole("USER","ADMIN")
		.requestMatchers(HttpMethod.POST,"/users").permitAll()
		.anyRequest().authenticated()
		)
		.httpBasic(withDefaults())
		.build();
	}
}
