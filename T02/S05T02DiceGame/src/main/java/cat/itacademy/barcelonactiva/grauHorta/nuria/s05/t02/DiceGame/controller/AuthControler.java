package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller.models.AuthResponse;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller.models.AuthenticationRequest;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller.models.RegisterRequest;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControler {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authService.authenticate(request));
    }
}
