package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    Boolean isTokenValid(String token, UserDetails userDetails);
}
