package com.kalimon.pokedex.controller.impl;

import com.kalimon.pokedex.controller.api.AuthApi;
import com.kalimon.pokedex.controller.dto.request.LoginRequest;
import com.kalimon.pokedex.controller.dto.request.RegisterRequest;
import com.kalimon.pokedex.controller.dto.response.TokenResponse;
import com.kalimon.pokedex.controller.mapper.UserDtoMapper;
import com.kalimon.pokedex.core.model.User;
import com.kalimon.pokedex.core.port.UserPersistencePort;
import com.kalimon.pokedex.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthenticationManager authenticationManager;
    private final UserPersistencePort userPersistencePort;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoMapper userDtoMapper;

    @Override
    public ResponseEntity<TokenResponse> register(RegisterRequest request) {
        User user = userDtoMapper.toDomain(request);
        User saved = userPersistencePort.save(
                user.toBuilder()
                        .password(passwordEncoder.encode(request.password()))
                        .build()
        );
        String token = generateToken(saved.getEmail(), saved.getRole());
        return ResponseEntity.ok(
                new TokenResponse(token, saved.getEmail(),
                        saved.getUsername(), saved.getRole()));
    }

    @Override
    public ResponseEntity<TokenResponse> login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(), request.password()));
        User user = userPersistencePort.findByEmail(request.email())
                .orElseThrow();
        String token = generateToken(user.getEmail(), user.getRole());
        return ResponseEntity.ok(
                new TokenResponse(token, user.getEmail(),
                        user.getUsername(), user.getRole()));
    }

    @Override
    public ResponseEntity<TokenResponse> oauth2Success(String token) {
        return ResponseEntity.ok(
                new TokenResponse(token, null, null, null));
    }

    private String generateToken(String email, String role) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(email)
                .password("")
                .roles(role)
                .build();
        return jwtService.generateToken(userDetails);
    }
}