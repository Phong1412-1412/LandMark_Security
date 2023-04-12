package com.phong1412.productsapi_security.security.jwt;

import com.phong1412.productsapi_security.repository.UserRepository;
import com.phong1412.productsapi_security.security.AppUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final UserRepository userRepository;
    @Value(value = "${spring.jwt.secret}")
    private String JWT_SECRET;
    private final long JWT_EXPIRATION_TIME = 1000 * 60 * 60;

    public String getGeneratedToken(String account) {
        Map<String, Object> claims = new HashMap<>();
        return generateTokenForUser(claims, account);
    }

    private String generateTokenForUser(Map<String, Object> claims, String account) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(account)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
                .setHeaderParam("typ", "JWT")
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Claims extractClaimsAll(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractClaimsAll(token);
        return claimsResolver.apply(claims);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserNameFromToken(String theToken) {
        return extractClaims(theToken, Claims::getSubject);
    }

    private Date extractExpirationTimeFromToken(String theToken) {
        return extractClaims(theToken, Claims::getExpiration);
    }

    private boolean isTokenExpiration(String theToken) {
        return extractExpirationTimeFromToken(theToken).before(new Date());
    }

    public boolean validateToken(String theToken, AppUserDetails userDetails) {
        final String account = extractUserNameFromToken(theToken);
        return (userDetails.getUserAccount().equals(account) && !isTokenExpiration(theToken));
    }
}
