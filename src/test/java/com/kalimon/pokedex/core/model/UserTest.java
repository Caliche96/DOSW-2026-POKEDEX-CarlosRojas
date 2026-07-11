package com.kalimon.pokedex.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    @DisplayName("builder: debe crear el objeto correctamente")
    void builder_createsCorrectly() {
        User user = User.builder()
                .id(1L)
                .email("kaliche@gmail.com")
                .username("Kaliche")
                .role("TRAINER")
                .active(true)
                .build();

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getEmail()).isEqualTo("kaliche@gmail.com");
        assertThat(user.getRole()).isEqualTo("TRAINER");
        assertThat(user.getActive()).isTrue();
    }

    @Test
    @DisplayName("toBuilder: debe crear copia modificada")
    void toBuilder_createsModifiedCopy() {
        User original = User.builder()
                .id(1L).email("kaliche@gmail.com").role("TRAINER").build();

        User modified = original.toBuilder().role("ADMIN").build();

        assertThat(modified.getRole()).isEqualTo("ADMIN");
        assertThat(original.getRole()).isEqualTo("TRAINER");
    }

    @Test
    @DisplayName("equals: dos User con mismos datos deben ser iguales")
    void equals_withSameData_returnsTrue() {
        User u1 = User.builder().id(1L).email("kaliche@gmail.com").build();
        User u2 = User.builder().id(1L).email("kaliche@gmail.com").build();

        assertThat(u1).isEqualTo(u2);
    }
}