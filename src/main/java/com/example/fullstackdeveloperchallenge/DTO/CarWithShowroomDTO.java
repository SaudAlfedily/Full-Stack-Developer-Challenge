package com.example.fullstackdeveloperchallenge.DTO;

import lombok.Data;

@Data
public class CarWithShowroomDTO {
    private String vin;
    private String maker;
    private String model;
    private Integer modelYear;
    private Double price;
    private String carShowroomName;
    private String contactNumber;
}
