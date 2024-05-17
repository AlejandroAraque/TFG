package com.example.tfg.auth;

import com.example.tfg.Model.Users;
import com.example.tfg.Repository.UserRepository;
import com.example.tfg.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Users.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        // Guardar el usuario en la base de datos
        Users savedUser = repository.save(user);

        // Obtener el ID generado por MongoDB
        String generatedId = savedUser.getId();
        System.out.println("Generated ID: " + generatedId);

        var jwtToken = jwtService.generateToken(savedUser);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .refreshToken(refreshToken)
                .build();

    }

}