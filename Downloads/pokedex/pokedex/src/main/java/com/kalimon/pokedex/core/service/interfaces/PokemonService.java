package com.kalimon.pokedex.core.service.interfaces;

import com.kalimon.pokedex.core.model.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PokemonService {
    Page<Pokemon> findAll(Pageable pageable);
    Pokemon findById(Long id);
    Pokemon create(Pokemon pokemon);
    Pokemon update(Long id, Pokemon pokemon);
    void delete(Long id);
}