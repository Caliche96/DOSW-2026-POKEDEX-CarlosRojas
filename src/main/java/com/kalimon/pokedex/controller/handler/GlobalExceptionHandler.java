package com.kalimon.pokedex.controller.handler;

import com.kalimon.pokedex.core.exception.BusinessException;
import com.kalimon.pokedex.core.exception.DuplicateResourceException;
import com.kalimon.pokedex.core.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public record ApiError(
            int status,
            String errorCode,
            String message,
            String path,
            LocalDateTime timestamp,
            List<FieldValidationError> fieldErrors
    ) {}

    public record FieldValidationError(String field, String message) {}

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(
            ResourceNotFoundException ex, HttpServletRequest req) {
        log.warn("Recurso no encontrado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(build(404, ex.getErrorCode(), ex.getMessage(),
                        req.getRequestURI(), List.of()));
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleDuplicate(
            DuplicateResourceException ex, HttpServletRequest req) {
        log.warn("Recurso duplicado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(build(409, ex.getErrorCode(), ex.getMessage(),
                        req.getRequestURI(), List.of()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException ex, HttpServletRequest req) {
        List<FieldValidationError> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> new FieldValidationError(e.getField(), e.getDefaultMessage()))
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(build(400, "VALIDATION_ERROR",
                        "Error de validación en los datos de entrada",
                        req.getRequestURI(), errors));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handleBusiness(
            BusinessException ex, HttpServletRequest req) {
        log.error("Error de negocio: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(build(422, ex.getErrorCode(), ex.getMessage(),
                        req.getRequestURI(), List.of()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneral(
            Exception ex, HttpServletRequest req) {
        log.error("Error inesperado: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(build(500, "INTERNAL_ERROR",
                        "Error interno del servidor",
                        req.getRequestURI(), List.of()));
    }

    private ApiError build(int status, String code, String message,
                           String path, List<FieldValidationError> errors) {
        return new ApiError(status, code, message, path, LocalDateTime.now(), errors);
    }
}