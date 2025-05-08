package com.ss.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf((csrf) -> csrf.disable()) // disables csrf protection
				.authorizeHttpRequests((authorize) -> { // ensures all http request must be authenticated
					authorize.anyRequest().authenticated();
				})

				.httpBasic(Customizer.withDefaults()); // enables http basic authentication

		return http.build();
	}
}
