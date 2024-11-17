package com.example.fullstackdeveloperchallenge.Service;

import com.example.fullstackdeveloperchallenge.ApiException.ApiException;
import com.example.fullstackdeveloperchallenge.DTO.CarShowroomDTO;
import com.example.fullstackdeveloperchallenge.DTO.CarShowroomUpdateDTO;
import com.example.fullstackdeveloperchallenge.Model.CarShowroom;
import com.example.fullstackdeveloperchallenge.Repository.CarShowroomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarShowroomService {
    private final CarShowroomRepository carShowroomRepository;
    private final ObjectMapper objectMapper;


    public CarShowroom createShowroom(@Valid CarShowroom showroom) {
        try{
        if (carShowroomRepository.existsByCommercialRegistrationNumber(showroom.getCommercialRegistrationNumber())) {
            throw new ApiException("Commercial registration number must be unique");
        }
        return carShowroomRepository.save(showroom);
    } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to create showroom: " + e.getMessage(), e);
        }
    }
    public Page<CarShowroomDTO> getCarShowrooms(int page, int size, String sortBy, String sortOrder) {
        Sort sort = Sort.by(Sort.Order.by(sortBy));
        if ("desc".equalsIgnoreCase(sortOrder)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<CarShowroom> showroomsPage = carShowroomRepository.findByActiveTrue(pageable);

        return showroomsPage.map(showroom -> objectMapper.convertValue(showroom, CarShowroomDTO.class));
    }

    public CarShowroom GetSpecificCarShowroom(Long id) {
        Optional<CarShowroom> optionalCarShowroom = carShowroomRepository.findByIdAndActiveTrue(id);
        if (optionalCarShowroom.isPresent()) {
            CarShowroom showroom = optionalCarShowroom.get();
            return showroom;
        } else {
            throw new ApiException("Showroom not found with id " + id);
        }
    }

    public CarShowroom updateCarShowroom(Long id, CarShowroomUpdateDTO carShowroomUpdateDTO) {
        Optional<CarShowroom> optionalCarShowroom = carShowroomRepository.findByIdAndActiveTrue(id);
        if (optionalCarShowroom.isPresent()) {
            CarShowroom showroom = optionalCarShowroom.get();
            showroom.setContactNumber(carShowroomUpdateDTO.getContactNumber());
            showroom.setManagerName(carShowroomUpdateDTO.getManagerName());
            showroom.setAddress(carShowroomUpdateDTO.getAddress());
            carShowroomRepository.save(showroom);
            return showroom;
        } else {
            throw new ApiException("Showroom not found with id " + id);

        }

    }

    public void softDeleteShowroom(Long id) {
        Optional<CarShowroom> optionalCarShowroom = carShowroomRepository.findByIdAndActiveTrue(id);
        if (optionalCarShowroom.isPresent()) {
            CarShowroom showroom = optionalCarShowroom.get();
            showroom.setActive(false);
            carShowroomRepository.save(showroom);
        } else {
            throw new ApiException("Showroom not found with id " + id);
        }
    }
}



