package swf.army.mil.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import swf.army.mil.backend.entity.CarEntity;
import swf.army.mil.backend.service.CarService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    public static final String HELLO_WORLD = "Hello World";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    CarService carService;

    private CarEntity car1;
    private CarEntity car2;
    private List<CarEntity> cars;

    @BeforeEach
    void setUp() {
        car1 = new CarEntity("Ford", "Mustang", 2000, 1222.22, true);
        car2 = new CarEntity("Toyota", "Camry", 1955, 20.21, false);
        cars = List.of(car1, car2);

        when(carService.getAllCars()).thenReturn(cars);

    }

    @Test
    void shouldReturnListOfCars() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        verify(carService, times(1)).getAllCars();
    }

    @Test
    void shouldAddNewCar() throws Exception {
        CarEntity car = new CarEntity("Toyota", "Camry", 1955, 20.21, false);
        car.setId(1L);

        Mockito.when(carService.addNewCar(any(CarEntity.class))).thenReturn(car);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(car1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(car.getId()))
                .andExpect(jsonPath("$.make").value(car.getMake()))
                .andExpect(jsonPath("$.model").value(car.getModel()))
                .andExpect(jsonPath("$.year").value(car.getYear()))
                .andExpect(jsonPath("$.price").value(car.getPrice()))
                .andExpect(jsonPath("$.used").value(car.getUsed()));


        verify(carService, times(1)).addNewCar(any(CarEntity.class));
    }

    @Test
    void shouldDeleteCarById() throws Exception{
        Mockito.when(carService.deleteCar(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/car/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
        verify(carService, times(1)).deleteCar(1L);
    }

    @Test
    void shouldUpdateCarById() throws  Exception {
        CarEntity carBeforeUpdates = new CarEntity("Ford", "Mustang", 2000, 1222.22, true);
        CarEntity carUpdates = new CarEntity("I have a new make", null, null, null, null);
        CarEntity carAfterUpdates = new CarEntity("I have a new make", "Mustang", 2000, 1222.22, true);


        when(carService.updateCarById(eq(1L), any(CarEntity.class))).thenReturn(carAfterUpdates);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/car/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carUpdates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carAfterUpdates.getId()))
                .andExpect(jsonPath("$.make").value(carAfterUpdates.getMake()))
                .andExpect(jsonPath("$.model").value(carAfterUpdates.getModel()))
                .andExpect(jsonPath("$.year").value(carAfterUpdates.getYear()))
                .andExpect(jsonPath("$.price").value(carAfterUpdates.getPrice()))
                .andExpect(jsonPath("$.used").value(carAfterUpdates.getUsed()));
    }
}