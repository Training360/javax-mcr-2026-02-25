package training.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @PostMapping
    public EmployeeDto joinEmployee(@RequestBody EmployeeDto employee) {
        return employeeService.joinEmployee(employee);
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable Long id) {
        return employeeService.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public EmployeeDto correct(@PathVariable Long id, @RequestBody EmployeeDto employee) {
        if (!employee.id().equals(id)) {
            throw new IllegalArgumentException("Employee id mismatch: %d != %d".formatted(employee.id(), id));
        }
        return employeeService.correct(employee);
    }

    @DeleteMapping("/{id}")
    public void leave(@PathVariable Long id) {
        employeeService.leave(id);
    }


}
