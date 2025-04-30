package swf.army.mil.backend.service;

import org.springframework.stereotype.Service;
import swf.army.mil.backend.entity.CarEntity;
import swf.army.mil.backend.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }
}
