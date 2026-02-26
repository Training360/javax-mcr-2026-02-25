package training.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeDto> findAll() {
        // CQRS: command and query responsibility segregation
        return employeeRepository
                .findAllBy(EmployeeDto.class);
    }

    public List<SummaryEmployeeDto> findAllSummary() {
        return employeeRepository
                .findAllBy(SummaryEmployeeDto.class);
    }

    public EmployeeDto joinEmployee(EmployeeDto employee) {
        Employee entity = new Employee(employee.name());
        Employee saved = employeeRepository.save(entity);
        return toDto(saved);
    }

    private EmployeeDto toDto(Employee entity) {
        return new EmployeeDto(entity.getId(), entity.getName());
    }

    public EmployeeDto findById(Long id) {
        return toDto(employeeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found: %d".formatted(id))));
    }

    @Transactional
    public EmployeeDto correct(EmployeeDto employee) {
        Employee entity = employeeRepository
                .findById(employee.id())
                .orElseThrow(() -> new NotFoundException("Employee not found: %d".formatted(employee.id())));
        entity.setName(employee.name());
        return toDto(entity);
    }

    public void leave(long id) {
        employeeRepository.deleteById(id);
    }

}
