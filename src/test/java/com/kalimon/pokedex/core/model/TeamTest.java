package com.kalimon.pokedex.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class TeamTest {

    @Test
    @DisplayName("builder: debe crear el objeto correctamente")
    void builder_createsCorrectly() {
        Pokemon pikachu = Pokemon.builder()
                .id(1L).name("Pikachu").nationalNumber(25).build();

        Team team = Team.builder()
                .id(1L)
                .name("Equipo Kaliche")
                .description("Mi equipo principal")
                .userId(1L)
                .pokemons(List.of(pikachu))
                .build();

        assertThat(team.getId()).isEqualTo(1L);
        assertThat(team.getName()).isEqualTo("Equipo Kaliche");
        assertThat(team.getPokemons()).hasSize(1);
        assertThat(team.getPokemons().get(0).getName()).isEqualTo("Pikachu");
    }

    @Test
    @DisplayName("toBuilder: debe crear copia modificada")
    void toBuilder_createsModifiedCopy() {
        Team original = Team.builder()
                .id(1L).name("Equipo A").userId(1L).build();

        Team modified = original.toBuilder().name("Equipo B").build();

        assertThat(modified.getName()).isEqualTo("Equipo B");
        assertThat(original.getName()).isEqualTo("Equipo A");
    }
}