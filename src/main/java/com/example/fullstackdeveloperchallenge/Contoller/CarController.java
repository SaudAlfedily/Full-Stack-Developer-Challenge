package com.example.fullstackdeveloperchallenge.Contoller;

import com.example.fullstackdeveloperchallenge.DTO.CarDTO;
import com.example.fullstackdeveloperchallenge.DTO.CarWithShowroomDTO;
import com.example.fullstackdeveloperchallenge.Model.Car;
import com.example.fullstackdeveloperchallenge.Service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody @Valid CarDTO carDTO) {
        return ResponseEntity.ok().body(carService.createCar(carDTO));

    }
    @GetMapping
    public ResponseEntity<Page<CarWithShowroomDTO>> listOfCars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "vin") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(value = "vin", required = false) String vin,
            @RequestParam(value = "maker", required = false) String maker,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "model_year", required = false) Integer modelYear,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "car_showroom_name", required = false) String showroomName,
            @RequestParam(value = "contact_number", required = false) String contactNumber
    ) {
        Page<CarWithShowroomDTO> carsPage = carService.getCarsWithShowroomDetails(page, size, sortBy, sortOrder,vin, maker, model, modelYear, price, showroomName, contactNumber);

        return ResponseEntity.ok(carsPage);
    }
}
