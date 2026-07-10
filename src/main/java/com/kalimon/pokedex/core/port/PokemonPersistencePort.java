package com.kalimon.pokedex.core.port;

import com.kalimon.pokedex.core.model.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface PokemonPersistencePort {
    Optional<Pokemon> findById(Long id);
    Page<Pokemon> findAll(Pageable pageable);
    boolean existsByNationalNumber(Integer number);
    Pokemon save(Pokemon pokemon);
    void deleteById(Long id);
}