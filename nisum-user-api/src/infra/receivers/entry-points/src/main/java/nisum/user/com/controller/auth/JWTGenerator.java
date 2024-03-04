package nisum.user.com.controller.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Objects;

public class JWTGenerator {

    @Value("${jwt.subject}")
    private String jwtSubject;

    @Value("${jwt.secret}")
    private String secretAuth;

    public String generate(String secret) {

        if (Objects.equals(secret, secretAuth))
            return Jwts.builder()
                    .setSubject(jwtSubject)
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                    .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                    .compact();

        return null;
    }
}
