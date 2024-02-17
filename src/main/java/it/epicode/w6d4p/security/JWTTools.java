package it.epicode.w6d4p.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.epicode.w6d4p.exception.UnauthorizedException;
import it.epicode.w6d4p.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {
    @Value("${security.secret}")
    private String secret;

    public String generateToken(User user){
        return Jwts.builder().subject(user.getEmail()).
                issuedAt(new Date(System.currentTimeMillis())).
                expiration(new Date(System.currentTimeMillis()+24*60*60*1000)).
                signWith(Keys.hmacShaKeyFor(secret.getBytes())).
                compact();
    }

    public void verifyToken(String token){
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        }
        catch (Exception e){
            throw new UnauthorizedException("Token scaduto o manomesso");
        }
    }

    public String  extractEmailFromToken(String token){
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parseSignedClaims(token).
                getPayload().getSubject();
    }
}
