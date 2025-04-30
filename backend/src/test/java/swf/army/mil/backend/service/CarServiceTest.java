package swf.army.mil.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import swf.army.mil.backend.entity.CarEntity;
import swf.army.mil.backend.repository.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    private CarEntity car1;
    private CarEntity car2;
    private CarEntity car3;
    private List<CarEntity> carList;

    @BeforeEach
    void setUp() {
        car1 = new CarEntity("Ford", "Mustang", 2000, 1222.22, true);
        car2 = new CarEntity("Toyota", "Camry", 1955, 20.21, false);
        car3 = new CarEntity("Ford", "Mustang", 2000, 1222.22, true);
        car3.setId(1L);
        carList = List.of(car1, car2);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllCars() {
        Mockito.when(carRepository.findAll()).thenReturn(carList);
        List<CarEntity> actualRequest = carService.getAllCars();
        verify(carRepository, times(1)).findAll();
        assertThat(actualRequest).isEqualTo(carList);
    }

    @Test
    void shouldAddNewCar(){
        Mockito.when(carRepository.save(any(CarEntity.class))).thenReturn(car3);
        CarEntity actualRequest = carService.addNewCar(car1);
        verify(carRepository,times(1)).save(car1);
        assertThat(actualRequest).isEqualTo(car3);
    }
}
