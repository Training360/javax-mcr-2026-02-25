package training.employees;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record EmployeeDto(
        @Schema(description = "az alkalmazott belső azonosítója", example = "1")
        Long id,

        @NotEmpty(message = "név nem lehet üres")
        @ValidName(ignored = {"dr.", "ifj."})
        @Schema(description = "az alkalmazott neve", example = "John Doe")
        String name) {
}
