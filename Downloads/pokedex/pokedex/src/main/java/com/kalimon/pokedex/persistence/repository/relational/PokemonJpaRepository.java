package com.kalimon.pokedex.persistence.repository.relational;

import com.kalimon.pokedex.persistence.entity.relational.PokemonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface PokemonJpaRepository extends JpaRepository<PokemonEntity, Long> {

    boolean existsByNationalNumber(Integer nationalNumber);

    @EntityGraph(attributePaths = {"types", "stats", "region"})
    @Query("SELECT p FROM PokemonEntity p WHERE p.id = :id")
    Optional<PokemonEntity> findByIdWithDetails(@Param("id") Long id);

    @EntityGraph(attributePaths = {"types", "region"})
    @Query("SELECT p FROM PokemonEntity p")
    Page<PokemonEntity> findAllWithTypes(Pageable pageable);
}