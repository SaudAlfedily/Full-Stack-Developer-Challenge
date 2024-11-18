package com.example.fullstackdeveloperchallenge.Repository;

import com.example.fullstackdeveloperchallenge.Model.CarShowroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarShowroomRepository extends JpaRepository<CarShowroom, Long> {
   boolean existsByCommercialRegistrationNumber(String commercialRegistrationNumber);

    Page<CarShowroom> findByActiveTrue(Pageable pageable);
    Optional<CarShowroom> findByCommercialRegistrationNumberAndActiveTrue(String id);


}
