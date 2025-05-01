package swf.army.mil.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import swf.army.mil.backend.entity.CarEntity;
import swf.army.mil.backend.repository.CarRepository;

import java.util.List;
import java.util.Optional;

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
        car1.setId(1L);
        car3.setId(3L);
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
    void shouldAddNewCar() {
        Mockito.when(carRepository.save(any(CarEntity.class))).thenReturn(car3);
        CarEntity actualRequest = carService.addNewCar(car1);
        verify(carRepository, times(1)).save(car1);
        assertThat(actualRequest).isEqualTo(car3);
    }

    @Nested
    class getByID {// Positive condition

        @Test
        void givenValidInput_shouldGetCarByID() {
            Mockito.when(carRepository.findById(1L)).thenReturn(Optional.of(car1));
            CarEntity actualRequest = carService.getCarById(1L);
            verify(carRepository, times(1)).findById(1L);
            assertThat(car1).isEqualTo(actualRequest);
        }


        // Negative condition
        @Test
        void givenInvalidInput_shouldReturnNullWhenGetCarById() {
            Mockito.when(carRepository.findById(4L)).thenReturn(Optional.empty());
            CarEntity actualRequest = carService.getCarById(4L);
            verify(carRepository, times(1)).findById(4L);
            assertThat(actualRequest).isNull();
        }
    }


    @Nested
    class deleteByID{
        @Test
        void givenValidInput_shouldDeleteCarByIDAndReturnTrue(){
            Mockito.when(carRepository.existsById(1L)).thenReturn(true);
            boolean result = carService.deleteCar(1L);
            assertThat(result).isTrue();
            verify(carRepository,times(1)).existsById(1L);
            verify(carRepository,times(1)).deleteById(1L);
        }
        @Test
        void givenInvalidInput_shouldNotDeleteCarByIDAndReturnFalse(){
            Mockito.when(carRepository.existsById(4L)).thenReturn(false);
            boolean result = carService.deleteCar(4L);
            assertThat(result).isFalse();
            verify(carRepository,times(1)).existsById(4L);
            verify(carRepository,times(0)).deleteById(4L);
        }
    }

    @Nested
    class updateById {
        @Test
        void givenValidInput_shouldUpdateCarById() {
            CarEntity carWithUpdates = new CarEntity("Ford", "bronco", 2000, 1222.22, true);
            Mockito.when(carRepository.findById(1L)).thenReturn(Optional.of(car1));

            CarEntity savedCar = carService.updateCarById(1L, carWithUpdates);

            verify(carRepository, times(1)).findById(1L);
            assert (savedCar.getModel().equals("bronco"));

        }

        @Test
        void givenInvalidInput_shouldNotUpdateCarAndReturnNull() {
            CarEntity carWithUpdates = new CarEntity("Ford", "bronco", 2000, 1222.22, true);
            Mockito.when(carRepository.findById(4L)).thenReturn(Optional.empty());

            CarEntity savedCar = carService.updateCarById(4L, carWithUpdates);

            verify(carRepository, times(1)).findById(4L);
            assertThat(savedCar).isNull();

        }
    }
}
