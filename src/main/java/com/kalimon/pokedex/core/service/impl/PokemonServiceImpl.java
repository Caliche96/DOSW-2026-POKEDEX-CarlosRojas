package com.kalimon.pokedex.core.service.impl;

import com.kalimon.pokedex.core.exception.DuplicateResourceException;
import com.kalimon.pokedex.core.exception.ResourceNotFoundException;
import com.kalimon.pokedex.core.model.Pokemon;
import com.kalimon.pokedex.core.port.PokemonPersistencePort;
import com.kalimon.pokedex.core.service.interfaces.PokemonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PokemonServiceImpl implements PokemonService {

    private final PokemonPersistencePort pokemonPort;

    @Override
    public Page<Pokemon> findAll(Pageable pageable) {
        log.debug("Listando todos los Pokémon");
        return pokemonPort.findAll(pageable);
    }

    @Override
    public Pokemon findById(Long id) {
        log.debug("Buscando Pokémon con id: {}", id);
        return pokemonPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon", "id", id));
    }

    @Override
    public Pokemon create(Pokemon pokemon) {
        log.info("Creando Pokémon: {}", pokemon.getName());
        if (pokemonPort.existsByNationalNumber(pokemon.getNationalNumber())) {
            throw new DuplicateResourceException("Pokemon", "nationalNumber",
                    pokemon.getNationalNumber());
        }
        return pokemonPort.save(pokemon);
    }

    @Override
    public Pokemon update(Long id, Pokemon pokemon) {
        log.info("Actualizando Pokémon con id: {}", id);
        findById(id);
        return pokemonPort.save(pokemon.toBuilder().id(id).build());
    }

    @Override
    public void delete(Long id) {
        log.info("Eliminando Pokémon con id: {}", id);
        findById(id);
        pokemonPort.deleteById(id);
    }
}