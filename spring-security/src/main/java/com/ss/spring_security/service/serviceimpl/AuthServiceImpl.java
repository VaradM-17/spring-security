package com.ss.spring_security.service.serviceimpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ss.spring_security.dao.RoleRepository;
import com.ss.spring_security.dao.UserRepository;
import com.ss.spring_security.dto.JwtAuthResponse;
import com.ss.spring_security.dto.LoginDto;
import com.ss.spring_security.dto.RegisterDto;
import com.ss.spring_security.entity.Role;
import com.ss.spring_security.entity.User;
import com.ss.spring_security.exception.TodoAPIException;
import com.ss.spring_security.security.JwtTokenProvider;
import com.ss.spring_security.service.AuthService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public String register(RegisterDto registerDto) {

		if (userRepository.existsByUserName(registerDto.getUserName())) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST, "UserName already exits");
		}

		if (userRepository.existsByEmail(registerDto.getEmail())) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email already exists");
		}

		User user = new User();

		user.setName(registerDto.getName());
		user.setUserName(registerDto.getUserName());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER");
		roles.add(userRole);

		user.setRoles(roles);

		userRepository.save(user);

		return "User Registered Successfully...";
	}

	@Override
	public JwtAuthResponse login(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenProvider.generateToken(authentication);
		Optional<User> userOptional = userRepository.findByUserNameOrEmail(loginDto.getUsernameOrEmail(),
				loginDto.getUsernameOrEmail());

		String role = null;

		if (userOptional.isPresent()) {
			User loggedInUser = userOptional.get();
			Optional<Role> optionalRole = loggedInUser.getRoles().stream().findFirst();

			if (optionalRole.isPresent()) {
				Role userRole = optionalRole.get();
				role = userRole.getName();
			}
		}

		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setRole(role);
		jwtAuthResponse.setAccessToken(token);

		return jwtAuthResponse;
	}

}
