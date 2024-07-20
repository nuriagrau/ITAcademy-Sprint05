package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller.models.AuthResponse;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller.models.AuthenticationRequest;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller.models.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthenticationRequest request);


}
