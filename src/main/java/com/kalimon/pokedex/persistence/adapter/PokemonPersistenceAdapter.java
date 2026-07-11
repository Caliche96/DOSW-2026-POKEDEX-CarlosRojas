package com.kalimon.pokedex.persistence.adapter;

import com.kalimon.pokedex.core.model.Pokemon;
import com.kalimon.pokedex.core.port.PokemonPersistencePort;
import com.kalimon.pokedex.persistence.mapper.PokemonPersistenceMapper;
import com.kalimon.pokedex.persistence.repository.relational.PokemonJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PokemonPersistenceAdapter implements PokemonPersistencePort {

    private final PokemonJpaRepository repository;
    private final PokemonPersistenceMapper mapper;

    @Override
    public Optional<Pokemon> findById(Long id) {
        return repository.findByIdWithDetails(id).map(mapper::toDomain);
    }

    @Override
    public Page<Pokemon> findAll(Pageable pageable) {
        return repository.findAllWithTypes(pageable).map(mapper::toDomain);
    }

    @Override
    public boolean existsByNationalNumber(Integer number) {
        return repository.existsByNationalNumber(number);
    }

    @Override
    public Pokemon save(Pokemon pokemon) {
        return mapper.toDomain(repository.save(mapper.toEntity(pokemon)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}