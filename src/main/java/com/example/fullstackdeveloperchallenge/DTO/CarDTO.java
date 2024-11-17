package com.example.fullstackdeveloperchallenge.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarDTO {
    @NotEmpty(message = "VIN is required")
    @Size(max = 25, message = "VIN should not exceed 25 characters")
    private String vin;

    @NotEmpty(message = "Maker is required")
    @Size(max = 25, message = "Maker should not exceed 25 characters")
    private String maker;

    @NotEmpty(message = "Model is required")
    @Size(max = 25, message = "Model should not exceed 25 characters")
    private String model;

    @NotNull(message = "Model year is required")
    @Min(value = 1886, message = "Model year must be a valid year (>= 1886)")
    private int modelYear;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull
    private Long showroomId;
}
