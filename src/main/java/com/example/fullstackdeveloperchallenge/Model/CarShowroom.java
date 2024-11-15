package com.example.fullstackdeveloperchallenge.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class CarShowroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name can be at most 100 characters")
    private String name;

    @NotBlank(message = "Commercial registration number is required")
    @Size(min = 10, max = 10, message = "Commercial registration number must be unique and exactly 10 digits")
    @Column(unique = true)
    private String commercialRegistrationNumber;

    @Size(max = 100, message = "Manager name can be at most 100 characters")
    private String managerName;
//don't forget  test -- and num and the leading zero
    @NotNull(message = "Contact number is required")
    @Pattern(regexp = "^[0-9]{1,15}$", message = "Contact number must be numeric and up to 15 digits")
    private String contactNumber;

    @Size(max = 255, message = "Address can be at most 255 characters")
    private String address;
}
