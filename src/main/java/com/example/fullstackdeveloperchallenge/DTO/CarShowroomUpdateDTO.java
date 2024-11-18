package com.example.fullstackdeveloperchallenge.DTO;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CarShowroomUpdateDTO {
    @NotNull(message = "Contact number is required")
    @Pattern(regexp = "^[0-9]{1,15}$", message = "Contact number must be numeric and up to 15 digits")
    private String contactNumber;
    @Size(max = 100, message = "Manager name can be at most 100 characters")
    private String managerName;
    @Size(max = 255, message = "Address can be at most 255 characters")
    private String address;
}
