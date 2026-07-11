package com.kalimon.pokedex.controller.mapper;

import com.kalimon.pokedex.controller.dto.request.PokemonRequest;
import com.kalimon.pokedex.controller.dto.request.PokemonStatsRequest;
import com.kalimon.pokedex.controller.dto.response.PokemonResponse;
import com.kalimon.pokedex.controller.dto.response.PokemonStatsResponse;
import com.kalimon.pokedex.core.model.Pokemon;
import com.kalimon.pokedex.core.model.PokemonStats;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PokemonDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hasMega", expression = "java(request.hasMega() != null ? request.hasMega() : false)")
    Pokemon toDomain(PokemonRequest request);

    PokemonResponse toResponse(Pokemon pokemon);

    PokemonStats toStats(PokemonStatsRequest request);

    PokemonStatsResponse toStatsResponse(PokemonStats stats);
}