package com.demo.spring_security.service;

import com.demo.spring_security.dto.LoginRequest;
import com.demo.spring_security.dto.LoginResponse;
import com.demo.spring_security.dto.SignupRequest;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    String register(SignupRequest signupRequest);
}
