package com.kalimon.pokedex.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class PokemonTest {

    @Test
    @DisplayName("builder: debe crear el objeto correctamente")
    void builder_createsCorrectly() {
        Pokemon pokemon = Pokemon.builder()
                .id(1L)
                .nationalNumber(25)
                .name("Pikachu")
                .types(List.of("Electric"))
                .region("Kanto")
                .generation(1)
                .hasMega(false)
                .build();

        assertThat(pokemon.getId()).isEqualTo(1L);
        assertThat(pokemon.getName()).isEqualTo("Pikachu");
        assertThat(pokemon.getTypes()).containsExactly("Electric");
    }

    @Test
    @DisplayName("toBuilder: debe crear copia modificada correctamente")
    void toBuilder_createsModifiedCopy() {
        Pokemon original = Pokemon.builder()
                .id(1L).name("Pikachu").nationalNumber(25).build();

        Pokemon modified = original.toBuilder().name("Raichu").build();

        assertThat(modified.getName()).isEqualTo("Raichu");
        assertThat(modified.getId()).isEqualTo(1L);
        assertThat(original.getName()).isEqualTo("Pikachu");
    }

    @Test
    @DisplayName("equals: dos Pokemon con mismos datos deben ser iguales")
    void equals_withSameData_returnsTrue() {
        Pokemon p1 = Pokemon.builder().id(1L).name("Pikachu").build();
        Pokemon p2 = Pokemon.builder().id(1L).name("Pikachu").build();

        assertThat(p1).isEqualTo(p2);
    }
}