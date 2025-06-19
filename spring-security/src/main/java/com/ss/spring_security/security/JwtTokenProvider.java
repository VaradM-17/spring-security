package com.ss.spring_security.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component 
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private Long jwtExpirationDate;

	// Method to generate JWT token using authenticated user's details
	public String generateToken(Authentication authentication) {
		String username = authentication.getName(); 
		Date now = new Date();
		Date expireDate = new Date(now.getTime() + jwtExpirationDate);

		// Build the JWT token with subject, issue time, expiration, and sign it
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(now)
				.setExpiration(expireDate)
				.signWith(key()) // Sign the token using the secret key
				.compact(); // Generate the final compact JWT string

		return token;
	}

	// Utility method to get the secret key in HMAC SHA format
	private Key key() {
		// Decode Base64 encoded secret and return as signing key
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	// Extract user name from the token
	public String getUsername(String token) {
		// Parse token and get claims (data inside the token)
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key()) // Set the secret key for validation
				.build()
				.parseClaimsJws(token) // Parse the signed JWT
				.getBody(); // Get the claims

		return claims.getSubject(); // Return the username stored as subject
	}

	// Validate the token's signature and structure
	public boolean validateToken(String token) {
		// If parsing succeeds without exception, the token is valid
		Jwts.parserBuilder()
			.setSigningKey(key())
			.build()
			.parse(token);
		return true;
	}
}
