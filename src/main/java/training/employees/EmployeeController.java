package training.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/names")
    public List<SummaryEmployeeDto> findAllSummary() {
        return employeeService.findAllSummary();
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> joinEmployee(@RequestBody EmployeeDto employee) {
        EmployeeDto joined = employeeService.joinEmployee(employee);
        // Még szebb megoldás: UriComponentsBuilder
        return ResponseEntity.created(URI.create("/api/employees/%d".formatted(joined.id()))).body(joined);
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PutMapping("/{id}")
    public EmployeeDto correct(@PathVariable Long id, @RequestBody EmployeeDto employee) {
        if (!employee.id().equals(id)) {
            throw new IllegalArgumentException("Employee id mismatch: %d != %d".formatted(employee.id(), id));
        }
        return employeeService.correct(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void leave(@PathVariable Long id) {
        employeeService.leave(id);
    }


}
