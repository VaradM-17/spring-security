package com.ss.spring_security.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ss.spring_security.entity.User;
import com.ss.spring_security.dao.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service 
@AllArgsConstructor 
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository; 

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
        // This method is called by Spring Security to load user during login

        // Find user by username or email from the database
        User user = userRepository.findByUserNameOrEmail(userNameOrEmail, userNameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

        // Convert user roles into a set of GrantedAuthority
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        // Return a Spring Security User object (contains username, password, and authorities)
        return new org.springframework.security.core.userdetails.User(
                userNameOrEmail, // Username or email used to login
                user.getPassword(), // Password (already encrypted)
                authorities // Roles or permissions
        );
    }
}
