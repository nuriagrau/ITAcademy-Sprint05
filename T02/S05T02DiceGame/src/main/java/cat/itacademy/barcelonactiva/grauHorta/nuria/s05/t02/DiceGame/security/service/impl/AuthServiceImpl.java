package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.service.impl;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.exceptions.UserAlreadyExistsException;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.enums.Role;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.dto.response.AuthResponse;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.dto.request.AuthenticationRequest;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.dto.request.RegisterRequest;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.User;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.UserRepository;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.service.AuthService;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.service.JwtService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;



    @PostConstruct
    public void createAdminIfDoesNotExist() {
        boolean adminExists = userRepository.findUserByEmail("admin@admin.com").isPresent();

        if (!adminExists) {
            User admin = User.builder()
                    .userName("admin")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("admin"))
                    .registrationDate(new Date())
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
        }
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null.");
        }
        userRepository.findUserByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
                });

        User user = User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .registrationDate(new Date())
                .role(request.getRole())
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("User with email " + request.getEmail() + " not found.")
        );
        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

}
