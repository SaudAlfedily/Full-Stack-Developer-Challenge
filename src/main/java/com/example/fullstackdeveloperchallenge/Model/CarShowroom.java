package com.example.fullstackdeveloperchallenge.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class CarShowroom {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name can be at most 100 characters")
    private String name;

    @NotBlank(message = "Commercial registration number is required")
    @Size(min = 10, max = 10, message = "Commercial registration number must be unique and exactly 10 digits")
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be numeric and up to 15 digits")
    @Id
    private String commercialRegistrationNumber;

    @Size(max = 100, message = "Manager name can be at most 100 characters")
    private String managerName;
    @NotNull(message = "Contact number is required")
    @Pattern(regexp = "^[0-9]{1,15}$", message = "Contact number must be numeric and up to 15 digits")
    private String contactNumber;

    @Size(max = 255, message = "Address can be at most 255 characters")
    private String address;

    private Boolean active=true;

    @OneToMany(mappedBy = "showroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Car> cars;
}
