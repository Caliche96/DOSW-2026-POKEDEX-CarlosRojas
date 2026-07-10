package com.kalimon.pokedex.persistence.entity.relational;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pokemon",
        indexes = {@Index(name = "idx_pokemon_number", columnList = "national_number")})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PokemonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "national_number", nullable = false, unique = true)
    private Integer nationalNumber;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "has_mega")
    @Builder.Default
    private Boolean hasMega = false;

    @Column(nullable = false)
    private Integer generation;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "pokemon_type",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    @Builder.Default
    private List<TypeEntity> types = new ArrayList<>();

    @OneToOne(mappedBy = "pokemon", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PokemonStatsEntity stats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private RegionEntity region;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}