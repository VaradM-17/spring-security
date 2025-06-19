package com.ss.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ss.spring_security.security.JwtAuthenticationEntryPoint;
import com.ss.spring_security.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration // Marks this class as a configuration class for Spring
@EnableMethodSecurity // Enables method-level security using annotations like @PreAuthorize
@AllArgsConstructor
public class SpringSecurityConfig {

	private UserDetailsService userDetailsService; // Injects custom UserDetailsService (used to load user from DB)
	private JwtAuthenticationEntryPoint authenticationEntyPoint;
	private JwtAuthenticationFilter authenticationFilter;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		// Bean for encoding passwords using BCrypt (recommended algorithm)
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// This configures which HTTP requests need authentication

		http.csrf(csrf -> csrf.disable()) // Disable CSRF (useful for APIs or development)
				.authorizeHttpRequests((auth) -> {

					auth.requestMatchers("/auth/**").permitAll();

					auth.anyRequest().authenticated(); // All requests must be authenticated
				}).httpBasic(Customizer.withDefaults()); // Use HTTP Basic authentication (username & password in
															// header)

		http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntyPoint));
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build(); // Build and return the SecurityFilterChain
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		// Provides an AuthenticationManager used to authenticate users
		return configuration.getAuthenticationManager();
	}
}
