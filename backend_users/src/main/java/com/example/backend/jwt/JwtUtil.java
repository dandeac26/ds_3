package com.example.backend.jwt;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
public class JwtUtil {
    private final String secret = "adbhile7UdUYdhPeNtfkjhsd6dLkktpN9875shP7iyo6=";

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10)) //10 hours
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean validateToken(String token, String username){
        final String usernameInToken = extractUsername(token);
        return(usernameInToken.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {return extractClaim(token, Claims::getSubject);}

    public Date extractExpiration(String token) { return extractClaim(token, Claims::getExpiration);}

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {return extractExpiration(token).before(new Date());}
}
