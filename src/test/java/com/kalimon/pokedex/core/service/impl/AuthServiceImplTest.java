package com.kalimon.pokedex.core.service.impl;

import com.kalimon.pokedex.core.exception.DuplicateResourceException;
import com.kalimon.pokedex.core.exception.ResourceNotFoundException;
import com.kalimon.pokedex.core.model.User;
import com.kalimon.pokedex.core.port.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserPersistencePort userPort;

    @InjectMocks
    private AuthServiceImpl service;

    private User kaliche;

    @BeforeEach
    void setUp() {
        kaliche = User.builder()
                .id(1L)
                .email("kaliche@gmail.com")
                .password("hashed_password")
                .username("Kaliche")
                .role("TRAINER")
                .active(true)
                .build();
    }

    @Test
    @DisplayName("register: debe guardar el usuario cuando el email no existe")
    void register_whenEmailNotExists_savesUser() {
        when(userPort.existsByEmail("kaliche@gmail.com")).thenReturn(false);
        when(userPort.save(kaliche)).thenReturn(kaliche);

        User result = service.register(kaliche);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("kaliche@gmail.com");
        verify(userPort).save(kaliche);
    }

    @Test
    @DisplayName("register: debe lanzar DuplicateResourceException si el email ya existe")
    void register_whenEmailExists_throwsException() {
        when(userPort.existsByEmail("kaliche@gmail.com")).thenReturn(true);

        assertThrows(DuplicateResourceException.class,
                () -> service.register(kaliche));
        verify(userPort, never()).save(any());
    }

    @Test
    @DisplayName("login: debe retornar token cuando el usuario existe")
    void login_whenUserExists_returnsToken() {
        when(userPort.findByEmail("kaliche@gmail.com")).thenReturn(Optional.of(kaliche));

        String token = service.login("kaliche@gmail.com", "password123");

        assertThat(token).isNotNull();
        verify(userPort).findByEmail("kaliche@gmail.com");
    }

    @Test
    @DisplayName("login: debe lanzar ResourceNotFoundException cuando el usuario no existe")
    void login_whenUserNotFound_throwsException() {
        when(userPort.findByEmail("noexiste@gmail.com")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.login("noexiste@gmail.com", "password"));
    }
}