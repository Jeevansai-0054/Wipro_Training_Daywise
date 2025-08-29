package com.wipro.ecom.user_service.security;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
	
	private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	private final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 *1000;
	
	public String getUsernameFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getSubject);
	}
	  public Date getExpirationDateFromToken(String token) {
	        return getClaimFromToken(token, Claims::getExpiration);
	    }

	    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
	        Claims claims = getAllClaimsFromToken(token);
	        return claimsResolver.apply(claims);
	    }

	    // For retrieving any information from token, secret key is needed
	    private Claims getAllClaimsFromToken(String token) {
	        return Jwts.parserBuilder()
	            .setSigningKey(secretKey)
	            .build()
	            .parseClaimsJws(token)
	            .getBody();
	    }

	    // Check if token is expired
	    private Boolean isTokenExpired(String token) {
	        final Date expiration = getExpirationDateFromToken(token);
	        return expiration.before(new Date());
	    }

	    // Generate token for user
	    public String generateToken(String username) {
	        return Jwts.builder()
	            .setSubject(username)
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
	            .signWith(secretKey)
	            .compact();
	    }

	    // Validate token
	    public Boolean validateToken(String token, String username) {
	        final String tokenUsername = getUsernameFromToken(token);
	        return (tokenUsername.equals(username) && !isTokenExpired(token));
	    }
	
	

}
