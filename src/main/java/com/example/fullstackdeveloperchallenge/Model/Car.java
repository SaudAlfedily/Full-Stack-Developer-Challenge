package com.example.fullstackdeveloperchallenge.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private double price;


    @ManyToOne
    @JoinColumn(name = "car_showroom_commercial_registration_number", nullable = false)
    private CarShowroom showroom;
}
