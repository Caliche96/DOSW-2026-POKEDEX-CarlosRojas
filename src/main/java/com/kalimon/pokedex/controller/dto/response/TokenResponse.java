package com.kalimon.pokedex.controller.dto.response;

public record TokenResponse(
        String token,
        String type,
        String email,
        String username,
        String role
) {
    public TokenResponse(String token, String email, String username, String role) {
        this(token, "Bearer", email, username, role);
    }
}