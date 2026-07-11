package com.kalimon.pokedex.persistence.repository.relational;

import com.kalimon.pokedex.persistence.entity.relational.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegionJpaRepository extends JpaRepository<RegionEntity, Long> {
    Optional<RegionEntity> findByName(String name);
}