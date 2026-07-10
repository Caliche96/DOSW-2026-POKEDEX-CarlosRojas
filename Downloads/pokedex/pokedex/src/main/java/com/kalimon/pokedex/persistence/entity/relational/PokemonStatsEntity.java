package com.kalimon.pokedex.persistence.entity.relational;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pokemon_stats")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PokemonStatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer hp;

    @Column(nullable = false)
    private Integer attack;

    @Column(nullable = false)
    private Integer defense;

    @Column(name = "special_attack", nullable = false)
    private Integer specialAttack;

    @Column(name = "special_defense", nullable = false)
    private Integer specialDefense;

    @Column(nullable = false)
    private Integer speed;

    @OneToOne
    @JoinColumn(name = "pokemon_id")
    private PokemonEntity pokemon;
}