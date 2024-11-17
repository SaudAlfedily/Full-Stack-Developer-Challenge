package com.example.fullstackdeveloperchallenge.Contoller;

import com.example.fullstackdeveloperchallenge.DTO.CarShowroomDTO;
import com.example.fullstackdeveloperchallenge.DTO.CarShowroomUpdateDTO;
import com.example.fullstackdeveloperchallenge.Model.CarShowroom;
import com.example.fullstackdeveloperchallenge.Service.CarShowroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/showrooms")
@RequiredArgsConstructor
public class CarShowroomController {
    private final CarShowroomService carShowroomService;

    @PostMapping
    public ResponseEntity<CarShowroom> createShowroom(@RequestBody @Valid CarShowroom showroom) {

        return ResponseEntity.status(200).body(carShowroomService.createShowroom(showroom));
    }

    @GetMapping
    public ResponseEntity<Page<CarShowroomDTO>> listCarShowrooms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<CarShowroomDTO> showroomsPage = carShowroomService.getCarShowrooms(page, size, sortBy, sortOrder);

        return ResponseEntity.ok(showroomsPage);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CarShowroom> getSpecificShowroom(@PathVariable Long id) {

        return ResponseEntity.ok(carShowroomService.GetSpecificCarShowroom(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarShowroom> updateShowroom(@PathVariable Long id, @RequestBody CarShowroomUpdateDTO carShowroomUpdateDTO) {
        return ResponseEntity.ok(carShowroomService.updateCarShowroom(id, carShowroomUpdateDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowroom(@PathVariable Long id) {

        carShowroomService.softDeleteShowroom(id);
        return ResponseEntity.noContent().build();
    }
}


