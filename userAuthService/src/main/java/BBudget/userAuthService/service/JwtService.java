package BBudget.userAuthService.service;

import BBudget.userAuthService.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Service
public class JwtService {
    @Value("$security.jwt.secret-key")
    private String SECRET_KEY;
    public String generateToken(User user){
        String token = Jwts
                .builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(SignatureAlgorithm.HS256,getSignKey())
                .compact();
        return token;
    };
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignKey())
                .parseClaimsJwt(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims,T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);

    }

    public String extractSubject(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public boolean isValid(String token,User user){
        String subject = extractSubject(token);
        return subject.equals(user.getEmail()) && !isTokenExpired((token));
    }


    private boolean isTokenExpired(String token){
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }

    public Long getExpiration(String token){
        return extractClaim(token,Claims::getExpiration).getTime();
    }

}
