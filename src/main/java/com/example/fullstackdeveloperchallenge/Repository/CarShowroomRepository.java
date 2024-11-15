package com.example.fullstackdeveloperchallenge.Repository;

import com.example.fullstackdeveloperchallenge.Model.CarShowroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarShowroomRepository extends JpaRepository<CarShowroom, Long> {
    Optional<CarShowroom> findByCommercialRegistrationNumber(String commercialRegistrationNumber);

}
