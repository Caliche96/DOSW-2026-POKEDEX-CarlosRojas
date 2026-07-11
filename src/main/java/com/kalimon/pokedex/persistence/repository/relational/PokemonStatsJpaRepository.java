package com.kalimon.pokedex.persistence.repository.relational;

import com.kalimon.pokedex.persistence.entity.relational.PokemonStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonStatsJpaRepository extends JpaRepository<PokemonStatsEntity, Long> {
}