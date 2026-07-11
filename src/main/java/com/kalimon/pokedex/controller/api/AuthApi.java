package com.kalimon.pokedex.controller.api;

import com.kalimon.pokedex.controller.dto.request.LoginRequest;
import com.kalimon.pokedex.controller.dto.request.RegisterRequest;
import com.kalimon.pokedex.controller.dto.response.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Auth", description = "Autenticación y registro de usuarios")
@RequestMapping("/v1/auth")
public interface AuthApi {

    @Operation(summary = "Registrar nuevo usuario")
    @PostMapping("/register")
    ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterRequest request);

    @Operation(summary = "Iniciar sesión con correo y contraseña")
    @PostMapping("/login")
    ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request);

    @Operation(summary = "Callback exitoso de OAuth2 Google")
    @GetMapping("/oauth2/success")
    ResponseEntity<TokenResponse> oauth2Success(@RequestParam String token);
}