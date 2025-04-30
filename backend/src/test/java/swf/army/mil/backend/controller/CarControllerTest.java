package swf.army.mil.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
    private  CarEntity car2;
    private List<CarEntity> cars;

    @BeforeEach
    void setUp() {
        car1=  new CarEntity("Ford", "Mustang", 2000, 1222.22, true);
        car2= new CarEntity("Toyota", "Camry", 1955, 20.21, false);
        cars = List.of(car1, car2);

        when(carService.getAllCars()).thenReturn(cars);
    }

    @Test
    void shouldReturnListOfCars() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        verify(carService,times(1)).getAllCars();
    }
}