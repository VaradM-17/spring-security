package com.ss.spring_security.service;

import com.ss.spring_security.dto.LoginDto;
import com.ss.spring_security.dto.RegisterDto;

public interface AuthService {

	String register(RegisterDto registerDto);

	String login(LoginDto loginDto);
}
