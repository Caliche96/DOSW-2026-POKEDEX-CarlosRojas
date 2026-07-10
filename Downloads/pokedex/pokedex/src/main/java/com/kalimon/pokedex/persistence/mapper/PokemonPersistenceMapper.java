package com.kalimon.pokedex.persistence.mapper;

import com.kalimon.pokedex.core.model.Pokemon;
import com.kalimon.pokedex.core.model.PokemonStats;
import com.kalimon.pokedex.persistence.entity.relational.PokemonEntity;
import com.kalimon.pokedex.persistence.entity.relational.PokemonStatsEntity;
import com.kalimon.pokedex.persistence.entity.relational.TypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PokemonPersistenceMapper {

    @Mapping(source = "region.name", target = "region")
    @Mapping(source = "types", target = "types", qualifiedByName = "typesToNames")
    @Mapping(source = "stats", target = "stats")
    Pokemon toDomain(PokemonEntity entity);

    @Mapping(target = "region", ignore = true)
    @Mapping(target = "types", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    PokemonEntity toEntity(Pokemon pokemon);

    @Mapping(target = "pokemon", ignore = true)
    PokemonStatsEntity toStatsEntity(PokemonStats stats);

    PokemonStats toStatsDomain(PokemonStatsEntity entity);

    @Named("typesToNames")
    default List<String> typesToNames(List<TypeEntity> types) {
        if (types == null) return List.of();
        return types.stream().map(TypeEntity::getName).toList();
    }
}