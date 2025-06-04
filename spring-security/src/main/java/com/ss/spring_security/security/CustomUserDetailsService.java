package com.ss.spring_security.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ss.spring_security.entity.User;
import com.ss.spring_security.dao.UserRepository;

import lombok.AllArgsConstructor;

@Service  
@AllArgsConstructor 
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository; 

    // This method is used by Spring Security to load user details during login
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        
        // Fetch user by username or email from the database
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User does not exits by username or email."));
        
        // Convert user roles to Spring Security authorities
        Set<GrantedAuthority> authorities = user.getRoles().stream()
            .map((role) -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toSet());
        
        // Return a Spring Security User object with authorities (password is passed as null here)
        return new org.springframework.security.core.userdetails.User(usernameOrEmail, null, authorities);
    }
}
