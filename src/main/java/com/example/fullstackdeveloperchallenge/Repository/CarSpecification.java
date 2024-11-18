package com.example.fullstackdeveloperchallenge.Repository;

import com.example.fullstackdeveloperchallenge.DTO.CarWithShowroomDTO;
import com.example.fullstackdeveloperchallenge.Model.Car;
import com.example.fullstackdeveloperchallenge.Model.CarShowroom;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecification {
    public static Specification<Car> withFilters(String vin, String maker, String model, Integer modelYear,
                                                 Double price, String showroomName, String contactNumber) {
        return (root, query, builder) -> {
            // Base predicate
            Predicate predicate = builder.conjunction(); // Initialize with "true"

// Apply filters with partial matching for all fields
            if (vin != null && !vin.isEmpty()) {
                predicate = builder.and(predicate, builder.like(root.get("vin"), "%" + vin + "%"));
            }

            if (maker != null && !maker.isEmpty()) {
                predicate = builder.and(predicate, builder.like(root.get("maker"), "%" + maker + "%"));
            }

            if (model != null && !model.isEmpty()) {
                predicate = builder.and(predicate, builder.like(root.get("model"), "%" + model + "%"));
            }

            if (modelYear != null) {
                predicate = builder.and(predicate, builder.like(root.get("modelYear").as(String.class), "%" + modelYear + "%"));
            }

            if (price != null) {
                predicate = builder.and(predicate, builder.equal(root.get("price"), price)); // Use exact match
            }

            if (showroomName != null && !showroomName.isEmpty()) {
                Join<Car, CarShowroom> showroomJoin = root.join("showroom", JoinType.INNER);
                predicate = builder.and(predicate, builder.like(showroomJoin.get("name"), "%" + showroomName + "%"));
            }

            if (contactNumber != null && !contactNumber.isEmpty()) {
                Join<Car, CarShowroom> showroomJoin = root.join("showroom", JoinType.INNER);
                predicate = builder.and(predicate, builder.like(showroomJoin.get("contactNumber"), "%" + contactNumber + "%"));
            }

// Return the combined predicate
            return predicate;

        };
    }
}
