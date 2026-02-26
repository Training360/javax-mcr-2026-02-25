package training.employees;

import java.io.Serializable;

/**
 * DTO for {@link Employee}
 */
public record SummaryEmployeeDto(String name) implements Serializable {
}