package com.kalimon.pokedex.controller.dto.response;

import java.util.List;

public record PokemonResponse(
        Long id,
        Integer nationalNumber,
        String name,
        String description,
        String imageUrl,
        List<String> types,
        String region,
        Integer generation,
        Boolean hasMega,
        PokemonStatsResponse stats
) {}