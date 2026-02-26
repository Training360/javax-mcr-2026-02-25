package training.employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Sql(statements = "DELETE FROM employee")
class EmployeesIT {

    RestTestClient restTestClient;

    @Autowired
    EmployeeController employeeController;

    @BeforeEach
    void createClient() {
        restTestClient = RestTestClient.bindToController(employeeController).build();
    }

//    @Test
    @RepeatedTest(10)
    void join() {
        var name = "John Doe %s".formatted(UUID.randomUUID());
        restTestClient
                .post()
                .uri("/api/employees")
                .body(new EmployeeDto(null, name))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EmployeeDto.class)
                .value(result -> {
                    assertTrue(result.id() > 0);
                    assertEquals(name, result.name());
                });

        restTestClient
                .get()
                .uri("/api/employees")
                .exchange()
                .expectBody(new ParameterizedTypeReference<List<EmployeeDto>>() {})
                .value(result ->
                        assertThat(result)
                                .extracting(EmployeeDto::name)
                                .contains(name)
                );
    }
}
