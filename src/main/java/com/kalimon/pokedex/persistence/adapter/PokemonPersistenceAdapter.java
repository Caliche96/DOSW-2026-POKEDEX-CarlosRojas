package com.kalimon.pokedex.persistence.adapter;

import com.kalimon.pokedex.core.model.Pokemon;
import com.kalimon.pokedex.core.port.PokemonPersistencePort;
import com.kalimon.pokedex.persistence.entity.relational.PokemonEntity;
import com.kalimon.pokedex.persistence.entity.relational.RegionEntity;
import com.kalimon.pokedex.persistence.entity.relational.TypeEntity;
import com.kalimon.pokedex.persistence.mapper.PokemonPersistenceMapper;
import com.kalimon.pokedex.persistence.repository.relational.PokemonJpaRepository;
import com.kalimon.pokedex.persistence.repository.relational.RegionJpaRepository;
import com.kalimon.pokedex.persistence.repository.relational.TypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.kalimon.pokedex.persistence.entity.relational.PokemonStatsEntity;
import com.kalimon.pokedex.persistence.repository.relational.PokemonStatsJpaRepository;

@Component
@RequiredArgsConstructor
public class PokemonPersistenceAdapter implements PokemonPersistencePort {

    private final PokemonJpaRepository repository;
    private final PokemonPersistenceMapper mapper;
    private final TypeJpaRepository typeRepository;
    private final RegionJpaRepository regionRepository;

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
        // Resolver tipos
        List<TypeEntity> typeEntities = new ArrayList<>();
        if (pokemon.getTypes() != null) {
            for (String typeName : pokemon.getTypes()) {
                TypeEntity typeEntity = typeRepository.findByName(typeName)
                        .orElseGet(() -> typeRepository.save(
                                TypeEntity.builder().name(typeName).build()
                        ));
                typeEntities.add(typeEntity);
            }
        }

        // Resolver región
        RegionEntity regionEntity = null;
        if (pokemon.getRegion() != null) {
            regionEntity = regionRepository.findByName(pokemon.getRegion())
                    .orElseGet(() -> regionRepository.save(
                            RegionEntity.builder().name(pokemon.getRegion()).build()
                    ));
        }

        // Construir entidad Pokémon
        PokemonEntity entityToSave = PokemonEntity.builder()
                .id(pokemon.getId())
                .nationalNumber(pokemon.getNationalNumber())
                .name(pokemon.getName())
                .description(pokemon.getDescription())
                .imageUrl(pokemon.getImageUrl())
                .hasMega(pokemon.getHasMega() != null ? pokemon.getHasMega() : false)
                .generation(pokemon.getGeneration())
                .types(typeEntities)
                .region(regionEntity)
                .build();

        // Guardar Pokémon primero
        PokemonEntity savedPokemon = repository.save(entityToSave);

        // Guardar stats vinculadas al Pokémon guardado
        if (pokemon.getStats() != null) {
            PokemonStatsEntity statsEntity = PokemonStatsEntity.builder()
                    .hp(pokemon.getStats().getHp())
                    .attack(pokemon.getStats().getAttack())
                    .defense(pokemon.getStats().getDefense())
                    .specialAttack(pokemon.getStats().getSpecialAttack())
                    .specialDefense(pokemon.getStats().getSpecialDefense())
                    .speed(pokemon.getStats().getSpeed())
                    .pokemon(savedPokemon)
                    .build();
            statsRepository.save(statsEntity);
        }

        // Recargar el Pokémon con todas las relaciones
        return repository.findByIdWithDetails(savedPokemon.getId())
                .map(mapper::toDomain)
                .orElseThrow();
    }
    private final PokemonStatsJpaRepository statsRepository;
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}