package com.example.fullstackdeveloperchallenge.Repository;

import com.example.fullstackdeveloperchallenge.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
