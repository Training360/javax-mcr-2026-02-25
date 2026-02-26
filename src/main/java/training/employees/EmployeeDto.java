package training.employees;

import jakarta.validation.constraints.NotEmpty;

public record EmployeeDto(
        Long id,

        @NotEmpty(message = "név nem lehet üres")
        @ValidName(ignored = {"dr.", "ifj."})
        String name) {
}
