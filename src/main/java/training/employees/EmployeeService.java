package training.employees;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService {

    private final AtomicLong idGenerator = new AtomicLong();

    private final List<EmployeeDto> employees = new CopyOnWriteArrayList<>(
            List.of(
                    new EmployeeDto(idGenerator.incrementAndGet(), "John Doe"),
                    new EmployeeDto(idGenerator.incrementAndGet(), "Jack Doe"))
    );

    public List<EmployeeDto> findAll() {
        return new ArrayList<>(employees);
    }

    public EmployeeDto joinEmployee(EmployeeDto employee) {
        EmployeeDto employeeToPersist = new EmployeeDto(idGenerator.incrementAndGet(), employee.name());
        employees.add(employeeToPersist);
        return employeeToPersist;
    }

    public Optional<EmployeeDto> findById(Long id) {
        return employees.stream().filter(employee -> employee.id().equals(id)).findFirst();
    }

    public EmployeeDto correct(EmployeeDto employee) {
        boolean exists = employees.removeIf(e -> e.id().equals(employee.id()));
        if (!exists) {
            throw new IllegalArgumentException("Employee not found: %d".formatted(employee.id()));
        }
        employees.add(employee);
        return employee;
    }

    public void leave(long id) {
        employees.removeIf(employee -> employee.id().equals(id));
    }

}
