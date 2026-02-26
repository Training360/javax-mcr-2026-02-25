package training.employees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Spy
    EmployeeMapper employeeMapper = new EmployeeMapperImpl();

    @InjectMocks
    EmployeeService employeeService;

    @Test
    void joinEmployee() {
//        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
//        EmployeeMapper employeeMapper = new EmployeeMapperImpl();
//        EmployeeService service = new EmployeeService(employeeRepository, employeeMapper);

        when(employeeRepository.save(any())).thenReturn(new Employee(1L, "John Doe"));
        var result = employeeService.joinEmployee(new EmployeeDto(null, "John Doe"));
        assertEquals("John Doe", result.name());
        assertEquals(1, result.id());
    }

    @Test
    void joinEmployeeEmployeeExists() {
        when(employeeRepository.findByName("John Doe")).thenReturn(Optional.of(new Employee(1L, "John Doe")));
        assertThrows(IllegalArgumentException.class,
                () -> employeeService.joinEmployee(new EmployeeDto(null, "John Doe")));
    }
}
