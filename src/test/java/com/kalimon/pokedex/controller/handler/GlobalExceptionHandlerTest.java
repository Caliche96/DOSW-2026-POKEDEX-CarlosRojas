package com.kalimon.pokedex.controller.handler;

import com.kalimon.pokedex.core.exception.BusinessException;
import com.kalimon.pokedex.core.exception.DuplicateResourceException;
import com.kalimon.pokedex.core.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;
    private MockHttpServletRequest request;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        request = new MockHttpServletRequest();
        request.setRequestURI("/api/v1/test");
    }

    @Test
    @DisplayName("handleNotFound: debe retornar 404 con errorCode NOT_FOUND")
    void handleNotFound_returns404() {
        ResourceNotFoundException ex =
                new ResourceNotFoundException("Pokemon", "id", 99L);

        ResponseEntity<GlobalExceptionHandler.ApiError> response =
                handler.handleNotFound(ex, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().status()).isEqualTo(404);
        assertThat(response.getBody().errorCode()).isEqualTo("NOT_FOUND");
        assertThat(response.getBody().message()).contains("Pokemon");
        assertThat(response.getBody().path()).isEqualTo("/api/v1/test");
        assertThat(response.getBody().timestamp()).isNotNull();
    }

    @Test
    @DisplayName("handleDuplicate: debe retornar 409 con errorCode DUPLICATE")
    void handleDuplicate_returns409() {
        DuplicateResourceException ex =
                new DuplicateResourceException("Pokemon", "nationalNumber", 25);

        ResponseEntity<GlobalExceptionHandler.ApiError> response =
                handler.handleDuplicate(ex, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody().status()).isEqualTo(409);
        assertThat(response.getBody().errorCode()).isEqualTo("DUPLICATE");
    }

    @Test
    @DisplayName("handleBusiness: debe retornar 422")
    void handleBusiness_returns422() {
        BusinessException ex = new BusinessException("Error de negocio", "BUSINESS_ERROR");

        ResponseEntity<GlobalExceptionHandler.ApiError> response =
                handler.handleBusiness(ex, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        assertThat(response.getBody().status()).isEqualTo(422);
        assertThat(response.getBody().errorCode()).isEqualTo("BUSINESS_ERROR");
    }

    @Test
    @DisplayName("handleGeneral: debe retornar 500")
    void handleGeneral_returns500() {
        Exception ex = new RuntimeException("Error inesperado");

        ResponseEntity<GlobalExceptionHandler.ApiError> response =
                handler.handleGeneral(ex, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody().status()).isEqualTo(500);
        assertThat(response.getBody().errorCode()).isEqualTo("INTERNAL_ERROR");
    }

    @Test
    @DisplayName("handleValidation: debe retornar 400 con fieldErrors")
    void handleValidation_returns400WithFieldErrors() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("pokemon", "name", "El nombre es obligatorio");

        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<GlobalExceptionHandler.ApiError> response =
                handler.handleValidation(ex, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().status()).isEqualTo(400);
        assertThat(response.getBody().fieldErrors()).hasSize(1);
        assertThat(response.getBody().fieldErrors().get(0).field()).isEqualTo("name");
    }

    @Test
    @DisplayName("ApiError: debe tener todos los campos")
    void apiError_hasAllFields() {
        ResourceNotFoundException ex =
                new ResourceNotFoundException("User", "email", "test@test.com");

        ResponseEntity<GlobalExceptionHandler.ApiError> response =
                handler.handleNotFound(ex, request);

        GlobalExceptionHandler.ApiError error = response.getBody();
        assertThat(error.status()).isEqualTo(404);
        assertThat(error.errorCode()).isNotNull();
        assertThat(error.message()).isNotNull();
        assertThat(error.path()).isNotNull();
        assertThat(error.timestamp()).isNotNull();
        assertThat(error.fieldErrors()).isNotNull();
    }
}