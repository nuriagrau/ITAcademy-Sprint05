package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller.models.AuthResponse;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller.models.AuthenticationRequest;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller.models.RegisterRequest;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.User;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.UserService;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @Operation(summary = "Register: create new user")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authService.register(request));
    }

    @Operation(summary = "Authenticate as user")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authService.authenticate(request));
    }

    @Operation(summary = "Get All Users")
    @GetMapping(path="/")
    public ResponseEntity<List<User>> getAllUsers() {

        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
}
