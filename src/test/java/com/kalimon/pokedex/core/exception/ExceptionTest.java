package com.kalimon.pokedex.core.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ExceptionTest {

    @Test
    @DisplayName("ResourceNotFoundException: debe tener el mensaje correcto")
    void resourceNotFoundException_hasCorrectMessage() {
        ResourceNotFoundException ex =
                new ResourceNotFoundException("Pokemon", "id", 99L);

        assertThat(ex.getMessage()).contains("Pokemon");
        assertThat(ex.getMessage()).contains("id");
        assertThat(ex.getMessage()).contains("99");
        assertThat(ex.getErrorCode()).isEqualTo("NOT_FOUND");
    }

    @Test
    @DisplayName("DuplicateResourceException: debe tener el mensaje correcto")
    void duplicateResourceException_hasCorrectMessage() {
        DuplicateResourceException ex =
                new DuplicateResourceException("Pokemon", "nationalNumber", 25);

        assertThat(ex.getMessage()).contains("Pokemon");
        assertThat(ex.getMessage()).contains("nationalNumber");
        assertThat(ex.getMessage()).contains("25");
        assertThat(ex.getErrorCode()).isEqualTo("DUPLICATE");
    }

    @Test
    @DisplayName("BusinessException: debe retornar el errorCode correcto")
    void businessException_returnsCorrectErrorCode() {
        BusinessException ex = new BusinessException("Error de prueba", "TEST_CODE");

        assertThat(ex.getErrorCode()).isEqualTo("TEST_CODE");
        assertThat(ex.getMessage()).isEqualTo("Error de prueba");
    }
}