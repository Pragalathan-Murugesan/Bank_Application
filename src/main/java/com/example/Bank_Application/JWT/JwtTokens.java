package com.example.Bank_Application.JWT;

import com.example.Bank_Application.Entity.CustomerEntity;
import com.example.Bank_Application.GlobalException.IllegalException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokens {
    public static final String secretKey = "This is secretKey";
    Long dateDuration = (long) (5 * 5);
    Long takenDate = System.currentTimeMillis();
    Long calculate = dateDuration + takenDate * 1000;

    Date issuedAt = new Date(takenDate);
    Date expiredDate = new Date(calculate);

    public String generateToken(CustomerEntity customerEntity) {
        Claims claims = Jwts.claims().setIssuer(customerEntity
                        .getUserName()).setIssuedAt(issuedAt)
                .setExpiration(expiredDate);
        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS384, secretKey).compact();
    }
    public Claims verifyToken(String authorization) throws Exception {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(authorization).getBody();
            return claims;
        } catch (IllegalException e) {
            throw new IllegalException("Unauthorized Access");
        }
    }
}