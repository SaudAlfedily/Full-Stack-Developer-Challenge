package com.example.fullstackdeveloperchallenge.Service;

import com.example.fullstackdeveloperchallenge.DTO.CarDTO;
import com.example.fullstackdeveloperchallenge.DTO.CarWithShowroomDTO;
import com.example.fullstackdeveloperchallenge.Model.Car;
import com.example.fullstackdeveloperchallenge.Model.CarShowroom;
import com.example.fullstackdeveloperchallenge.Repository.CarRepository;
import com.example.fullstackdeveloperchallenge.Repository.CarShowroomRepository;
import com.example.fullstackdeveloperchallenge.Repository.CarSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarShowroomRepository carShowroomRepository;
    private final CarRepository carRepository;
    private final ObjectMapper objectMapper;

    public Car createCar(@Valid CarDTO carDTO) {
        CarShowroom showroom = carShowroomRepository.findByIdAndActiveTrue(carDTO.getShowroomId())
                .orElseThrow(() -> new EntityNotFoundException("Showroom not found with ID " + carDTO.getShowroomId()));

        try {
            if (carRepository.existsByVin(carDTO.getVin())) {
                throw new IllegalArgumentException("VIN must be unique");
            }

            Car car = objectMapper.convertValue(carDTO, Car.class);

            car.setShowroom(showroom);

            return carRepository.save(car);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to map DTO to Entity", e);
        }
    }

    public Page<CarWithShowroomDTO> getCarsWithShowroomDetails(int page, int size, String sortBy, String sortOrder, String vin, String maker, String model, Integer modelYear,
                                                               Double price, String showroomName, String contactNumber) {
        try {


            Sort sort = Sort.by(Sort.Order.by(sortBy));
            if ("desc".equalsIgnoreCase(sortOrder)) {
                sort = sort.descending();
            } else {
                sort = sort.ascending();
            }

            Pageable pageable = PageRequest.of(page, size, sort);

            Specification<Car> spec = CarSpecification.withFilters(vin, maker, model, modelYear, price, showroomName, contactNumber);

            Page<Car> carPage = carRepository.findAll(spec, pageable);

            return carPage.map(car -> {

                CarWithShowroomDTO carDTO = objectMapper.convertValue(car, CarWithShowroomDTO.class);


                CarShowroom showroom = car.getShowroom();
                if (showroom != null) {
                    carDTO.setCarShowroomName(showroom.getName());
                    carDTO.setContactNumber(showroom.getContactNumber());
                }

                return carDTO;
            });
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid filter parameters: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred while fetching cars: " + e.getMessage(), e);
        }

    }

}
