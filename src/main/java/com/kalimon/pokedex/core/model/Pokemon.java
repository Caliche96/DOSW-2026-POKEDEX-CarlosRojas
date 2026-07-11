package com.kalimon.pokedex.core.model;

import lombok.Builder;
import lombok.Value;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class Pokemon {
    Long id;
    Integer nationalNumber;
    String name;
    String description;
    String imageUrl;
    List<String> types;
    String region;
    Integer generation;
    Boolean hasMega;
    PokemonStats stats;
}