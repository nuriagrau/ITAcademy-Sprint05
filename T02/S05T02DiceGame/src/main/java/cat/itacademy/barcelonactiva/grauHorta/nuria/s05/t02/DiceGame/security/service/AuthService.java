package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.service;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.dto.response.AuthResponse;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.dto.request.AuthenticationRequest;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.dto.request.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthenticationRequest request);


}
