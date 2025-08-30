package com.wipro.ecom.product_service.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Component
public class JwtUtil {

	 @Value("${security.jwt.secret}")
	    private String secret;

	    public Claims extractClaims(String token) {
	        Key key = Keys.hmacShaKeyFor(secret.getBytes());
	        return Jwts.parserBuilder()
	                .setSigningKey(key)
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }

	    public String extractRole(String token) {
	        return extractClaims(token).get("role", String.class);
	    }

	    public String extractUsername(String token) {
	        return extractClaims(token).getSubject();
	    }
}
