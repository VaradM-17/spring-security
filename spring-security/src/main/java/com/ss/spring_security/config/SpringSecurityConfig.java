package com.ss.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Marks this class as configuration for Spring Security setup
public class SpringSecurityConfig {

	// Bean to create a PasswordEncoder using BCrypt for securely hashing passwords
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Security filter chain to configure HTTP security
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// Disable CSRF protection
		http.csrf((csrf) -> csrf.disable())

				// Require authentication for all HTTP requests
				.authorizeHttpRequests((authorize) -> {
					authorize.requestMatchers(HttpMethod.POST, "/todos/**").hasRole("ADMIN");
					authorize.requestMatchers(HttpMethod.PUT, "/todos/**").hasRole("ADMIN");
					authorize.requestMatchers(HttpMethod.DELETE,"/todos/**").hasRole("ADMIN");
					authorize.requestMatchers(HttpMethod.GET,"/todos/**").hasAnyRole("ADMIN","USER");
					authorize.requestMatchers(HttpMethod.PATCH,"/todos/**").hasAnyRole("ADMIN","USER");
					authorize.anyRequest().authenticated();
				})

				// Enable HTTP Basic authentication
				.httpBasic(Customizer.withDefaults());

		// Return the security filter chain configuration
		return http.build();
	}

	// Bean to create an in-memory UserDetailsService
	@Bean
	public UserDetailsService userDetailsService() {

		// Create user 1
		UserDetails varad = User.builder()
				.username("varad")
				.password(passwordEncoder().encode("password")) // Password encoding
				.roles("USER").build();

		// Create user 2
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("admin")) // Password encoding
				.roles("ADMIN").build();

		// Return an in-memory user details manager for authentication
		return new InMemoryUserDetailsManager(varad, admin);
	}
}
