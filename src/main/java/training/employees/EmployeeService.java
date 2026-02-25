package training.employees;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

}
