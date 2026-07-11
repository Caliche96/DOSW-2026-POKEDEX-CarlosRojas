package com.kalimon.pokedex.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Tag(name = "Teams", description = "Gestión de equipos Pokémon")
@RequestMapping("/v1/teams")
@SecurityRequirement(name = "Bearer Authentication")
public interface TeamApi {

    @Operation(summary = "Listar mis equipos")
    @GetMapping
    ResponseEntity<List<Map<String, Object>>> getMyTeams();

    @Operation(summary = "Crear un equipo")
    @PostMapping
    ResponseEntity<Map<String, Object>> createTeam(
            @RequestBody Map<String, Object> request);

    @Operation(summary = "Eliminar un equipo")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTeam(@PathVariable Long id);
}