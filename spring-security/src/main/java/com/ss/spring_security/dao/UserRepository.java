package com.ss.spring_security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.spring_security.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUserName(String username);
	
	Boolean existsByEmail(String email);
	
	Optional<User> findByUserNameOrEmail(String username , String email);
}
