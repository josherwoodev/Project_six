package swf.army.mil.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swf.army.mil.backend.entity.CarEntity;
import swf.army.mil.backend.service.CarService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarEntity>> getAllCars()
    {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PostMapping("/cars")
    public ResponseEntity<CarEntity> addNewCar(@RequestBody CarEntity car)
    {
        return ResponseEntity.ok(carService.addNewCar(car));
    }


    @DeleteMapping("/car/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable Long id)
    {
        return ResponseEntity.ok(carService.deleteCar(id));

    }

    @PatchMapping("/car/{id}")
    public ResponseEntity<CarEntity> updateCarById(@PathVariable Long id, @RequestBody CarEntity car) {
        return ResponseEntity.ok(carService.updateCarById(id, car));
    }

}
