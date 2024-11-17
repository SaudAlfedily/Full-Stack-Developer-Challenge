package com.example.fullstackdeveloperchallenge.ControllerAdvise;


import com.example.fullstackdeveloperchallenge.ApiException.ApiException;
import com.example.fullstackdeveloperchallenge.ApiResponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvise {

    // Handle custom exceptions (e.g., ApiException)
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleApiException(ApiException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handle NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException e) {
        return buildErrorResponse("A null pointer exception occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle MethodArgumentNotValidException for validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // Collect all validation error messages from the binding result
        List<String> errorMessages = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return buildErrorResponse("Validation failed: " + String.join(", ", errorMessages), HttpStatus.BAD_REQUEST);
    }

    // Handle MethodArgumentTypeMismatchException (incorrect parameter types)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return buildErrorResponse("Invalid argument type: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handle DataIntegrityViolationException (typically for constraint violations in the DB)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return buildErrorResponse("Data integrity violation: " + e.getMessage(), HttpStatus.CONFLICT);
    }

    // Handle HttpMessageNotReadableException (invalid JSON format in request body)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return buildErrorResponse("Invalid JSON in request body: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handle TransactionSystemException (issues during transaction commit)
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ApiResponse> handleTransactionSystemException(TransactionSystemException e) {
        return buildErrorResponse("Transaction error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle ConcurrentModificationException (simultaneous updates to the same resource)
    @ExceptionHandler(ConcurrentModificationException.class)
    public ResponseEntity<ApiResponse> handleConcurrentModificationException(ConcurrentModificationException e) {
        return buildErrorResponse("Concurrent modification detected: " + e.getMessage(), HttpStatus.CONFLICT);
    }

    // General fallback for all other uncaught exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception e) {
        return buildErrorResponse("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Helper method to build standardized error response
    private ResponseEntity<ApiResponse> buildErrorResponse(String message, HttpStatus status) {
        ApiResponse apiResponse = new ApiResponse(message);
        return new ResponseEntity<>(apiResponse, status);
    }
}
