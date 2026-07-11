package com.kalimon.pokedex.core.service.impl;

import com.kalimon.pokedex.core.exception.DuplicateResourceException;
import com.kalimon.pokedex.core.exception.ResourceNotFoundException;
import com.kalimon.pokedex.core.model.Pokemon;
import com.kalimon.pokedex.core.model.PokemonStats;
import com.kalimon.pokedex.core.port.PokemonPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonServiceImplTest {

    @Mock
    private PokemonPersistencePort pokemonPort;

    @InjectMocks
    private PokemonServiceImpl service;

    private Pokemon pikachu;

    @BeforeEach
    void setUp() {
        pikachu = Pokemon.builder()
                .id(1L)
                .nationalNumber(25)
                .name("Pikachu")
                .types(List.of("Electric"))
                .region("Kanto")
                .generation(1)
                .hasMega(false)
                .stats(PokemonStats.builder()
                        .hp(35).attack(55).defense(40)
                        .specialAttack(50).specialDefense(50).speed(90)
                        .build())
                .build();
    }

    @Test
    @DisplayName("findById: debe retornar el Pokémon cuando existe")
    void findById_whenExists_returnsPokemon() {
        when(pokemonPort.findById(1L)).thenReturn(Optional.of(pikachu));

        Pokemon result = service.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Pikachu");
        assertThat(result.getNationalNumber()).isEqualTo(25);
        verify(pokemonPort).findById(1L);
    }

    @Test
    @DisplayName("findById: debe lanzar ResourceNotFoundException cuando no existe")
    void findById_whenNotFound_throwsException() {
        when(pokemonPort.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.findById(99L));
        verify(pokemonPort).findById(99L);
    }

    @Test
    @DisplayName("create: debe guardar el Pokémon cuando el número no existe")
    void create_whenNumberNotExists_savesPokemon() {
        when(pokemonPort.existsByNationalNumber(25)).thenReturn(false);
        when(pokemonPort.save(pikachu)).thenReturn(pikachu);

        Pokemon result = service.create(pikachu);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Pikachu");
        verify(pokemonPort).save(pikachu);
    }

    @Test
    @DisplayName("create: debe lanzar DuplicateResourceException si el número ya existe")
    void create_whenNumberExists_throwsException() {
        when(pokemonPort.existsByNationalNumber(25)).thenReturn(true);

        assertThrows(DuplicateResourceException.class,
                () -> service.create(pikachu));
        verify(pokemonPort, never()).save(any());
    }

    @Test
    @DisplayName("delete: debe eliminar el Pokémon cuando existe")
    void delete_whenExists_deletesPokemon() {
        when(pokemonPort.findById(1L)).thenReturn(Optional.of(pikachu));
        doNothing().when(pokemonPort).deleteById(1L);

        service.delete(1L);

        verify(pokemonPort).deleteById(1L);
    }

    @Test
    @DisplayName("delete: debe lanzar ResourceNotFoundException cuando no existe")
    void delete_whenNotFound_throwsException() {
        when(pokemonPort.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.delete(99L));
        verify(pokemonPort, never()).deleteById(any());
    }

    @Test
    @DisplayName("update: debe actualizar el Pokémon cuando existe")
    void update_whenExists_updatesPokemon() {
        Pokemon updated = pikachu.toBuilder().name("Raichu").build();
        when(pokemonPort.findById(1L)).thenReturn(Optional.of(pikachu));
        when(pokemonPort.save(any())).thenReturn(updated);

        Pokemon result = service.update(1L, pikachu);

        assertThat(result.getName()).isEqualTo("Raichu");
        verify(pokemonPort).save(any());
    }

    @Test
    @DisplayName("stats: getTotal debe retornar la suma correcta")
    void stats_getTotal_returnsCorrectSum() {
        PokemonStats stats = pikachu.getStats();
        assertThat(stats.getTotal()).isEqualTo(320);
    }
}