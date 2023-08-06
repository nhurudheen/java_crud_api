package com.example.crud_api.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTConfig {
    private static final String SECRET_KEY = "Nhurudh33n";
    private static final long EXPIRATION_TIME_MS = 2 * 60 * 60 * 1000; // 2 hours
    public static String generateToken(String email, String password) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + EXPIRATION_TIME_MS);

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .claim("password", password)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return token;
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Claims extractClaims(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        return claimsJws.getBody();
    }

    public static boolean isTokenExpired(String token) {
        Claims claims = extractClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }
}
