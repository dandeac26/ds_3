package com.example.backend.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    @Value("adbhile7UdUYdhPeNtfkjhsd6dLkktpN9875shP7iyo6=")
    private String secretKey;

    public Claims extractAllClaims(String token) throws SignatureException {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) { return extractAllClaims(token).getSubject();}

    public Boolean validateToken(String token){
        try{
            extractAllClaims(token);
            System.out.println("Token validated!\n");
            return true;
        }catch(SignatureException ex){
//            Token NOT validated!
        }
        System.out.println("\nVALIDATION FAILED!!!\n");
        return false;
    }
}
