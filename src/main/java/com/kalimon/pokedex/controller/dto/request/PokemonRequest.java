package com.kalimon.pokedex.controller.dto.request;

import jakarta.validation.constraints.*;
import java.util.List;

public record PokemonRequest(
        @NotNull(message = "El número nacional es obligatorio")
        @Min(value = 1, message = "El número debe ser mayor a 0")
        Integer nationalNumber,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
        String name,

        @NotBlank(message = "La imagen es obligatoria")
        String imageUrl,

        @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
        String description,

        @NotEmpty(message = "Debe tener al menos un tipo")
        @Size(max = 2, message = "Un Pokémon puede tener máximo 2 tipos")
        List<String> types,

        @NotBlank(message = "La región es obligatoria")
        String region,

        @NotNull(message = "La generación es obligatoria")
        @Min(value = 1, message = "La generación mínima es 1")
        @Max(value = 9, message = "La generación máxima es 9")
        Integer generation,

        Boolean hasMega,

        PokemonStatsRequest stats
) {}