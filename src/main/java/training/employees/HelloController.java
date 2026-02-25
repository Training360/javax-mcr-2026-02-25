package training.employees;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HelloController {

    private final HelloService helloService;

    @GetMapping("/hello")
    public String hello(HelloParams params) {
        log.info("Hello record %s %s %s".formatted(params.name(), params.yearOfBirth(), params.salary()));
        return helloService.hello();
    }
}
