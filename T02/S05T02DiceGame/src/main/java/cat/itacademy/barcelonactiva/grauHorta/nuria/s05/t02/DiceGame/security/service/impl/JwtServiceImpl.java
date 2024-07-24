package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.service.impl;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Value(value = "${jwt.secret}")
    private String secretKey;


    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {

        return Jwts.builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails) {

        return generateToken(new HashMap<>(), userDetails);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);

        return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {

        return getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }

}
