package swf.army.mil.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swf.army.mil.backend.entity.CarEntity;
import swf.army.mil.backend.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private static final Logger log = LoggerFactory.getLogger(CarService.class);
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }

    public CarEntity addNewCar(CarEntity car1) {
        return carRepository.save(car1);
    }

    public CarEntity getCarById(Long l) {
        return carRepository.findById(l).orElse(null);
    }

    public boolean deleteCar(Long l) {
        if (carRepository.existsById(l)) {
            carRepository.deleteById(l);
            return true;
        }
        return false;
    }

    public CarEntity updateCarById(Long l, CarEntity carWithUpdates) {
        CarEntity carToEdit = carRepository.findById(l).orElse(null);
        if(carToEdit != null) {
            if (carWithUpdates.getMake() != null) {carToEdit.setMake(carWithUpdates.getMake()); }
            if (carWithUpdates.getModel() != null) {carToEdit.setModel(carWithUpdates.getModel()); }
            if (carWithUpdates.getYear() != null) {carToEdit.setYear(carWithUpdates.getYear()); }
            if (carWithUpdates.getPrice() != null) {carToEdit.setPrice(carWithUpdates.getPrice()); }
            if (carWithUpdates.getUsed() != null) {carToEdit.setUsed(carWithUpdates.getUsed()); }

            // Updates done. Return new car
            return carRepository.save(carToEdit);
        }

        return null;
    }
}
