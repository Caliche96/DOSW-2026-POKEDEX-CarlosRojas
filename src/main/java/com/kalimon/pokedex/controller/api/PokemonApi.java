package com.kalimon.pokedex.controller.api;

import com.kalimon.pokedex.controller.dto.request.PokemonRequest;
import com.kalimon.pokedex.controller.dto.response.PokemonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pokemon", description = "Gestión del catálogo de Pokémon")
@RequestMapping("/v1/pokemon")
public interface PokemonApi {

    @Operation(summary = "Listar todos los Pokémon", description = "Acceso público")
    @GetMapping
    ResponseEntity<Page<PokemonResponse>> findAll(
            @PageableDefault(size = 20, sort = "nationalNumber") Pageable pageable);

    @Operation(summary = "Obtener Pokémon por ID")
    @GetMapping("/{id}")
    ResponseEntity<PokemonResponse> findById(@PathVariable Long id);

    @Operation(summary = "Crear Pokémon — solo ADMIN")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    ResponseEntity<PokemonResponse> create(@Valid @RequestBody PokemonRequest request);

    @Operation(summary = "Actualizar Pokémon — solo ADMIN")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}")
    ResponseEntity<PokemonResponse> update(@PathVariable Long id,
                                           @Valid @RequestBody PokemonRequest request);

    @Operation(summary = "Eliminar Pokémon — solo ADMIN")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}