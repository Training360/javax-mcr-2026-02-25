package training.employees;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HelloService {

    public String hello() {
        return "Hello World! %s".formatted(LocalDateTime.now());
    }
}
