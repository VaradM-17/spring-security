package com.ss.spring_security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.spring_security.dto.LoginDto;
import com.ss.spring_security.dto.RegisterDto;
import com.ss.spring_security.service.AuthService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/auth/")
@RestController
public class AuthController {

	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

		String response = authService.register(registerDto);
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
		
		String response = authService.login(loginDto);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
