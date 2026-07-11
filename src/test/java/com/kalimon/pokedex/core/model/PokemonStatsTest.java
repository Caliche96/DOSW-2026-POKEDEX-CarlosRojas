package com.kalimon.pokedex.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PokemonStatsTest {

    @Test
    @DisplayName("getTotal: debe sumar correctamente los 6 stats")
    void getTotal_returnsCorrectSum() {
        PokemonStats stats = PokemonStats.builder()
                .hp(45).attack(49).defense(49)
                .specialAttack(65).specialDefense(65).speed(45)
                .build();

        assertThat(stats.getTotal()).isEqualTo(318);
    }

    @Test
    @DisplayName("getTotal: debe retornar 0 cuando todos los stats son 0")
    void getTotal_withZeroStats_returnsZero() {
        PokemonStats stats = PokemonStats.builder()
                .hp(0).attack(0).defense(0)
                .specialAttack(0).specialDefense(0).speed(0)
                .build();

        assertThat(stats.getTotal()).isEqualTo(0);
    }

    @Test
    @DisplayName("builder: debe crear el objeto correctamente")
    void builder_createsCorrectly() {
        PokemonStats stats = PokemonStats.builder()
                .hp(35).attack(55).defense(40)
                .specialAttack(50).specialDefense(50).speed(90)
                .build();

        assertThat(stats.getHp()).isEqualTo(35);
        assertThat(stats.getAttack()).isEqualTo(55);
        assertThat(stats.getSpeed()).isEqualTo(90);
    }
}