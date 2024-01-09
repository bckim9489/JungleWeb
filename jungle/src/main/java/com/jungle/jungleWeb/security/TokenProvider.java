package com.jungle.jungleweb.security;

import com.jungle.jungleweb.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {

    //@Value("${custom.jwt.secret}")
    private final String SECRET_KEY = "";

    byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);

    Key key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA512");

    public String create(User user){
        Date expireDate = Date.from(Instant.now().plus(600, ChronoUnit.SECONDS));

        return Jwts.builder()
                .signWith(key, io.jsonwebtoken.SignatureAlgorithm.HS512)
                .setSubject(user.getUserId())
                .setIssuer("jungle web")
                .setIssuedAt(expireDate)
                .compact();
    }

    public String validateAndGetUserId(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

}
