package training.employees;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    <T> List<T> findAllBy(Class<T> clazz);

    Optional<Employee> findByName(String name);
}