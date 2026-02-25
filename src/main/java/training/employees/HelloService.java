package training.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableConfigurationProperties(HelloConfigurationProperties.class)
@RequiredArgsConstructor
public class HelloService {

    private final HelloConfigurationProperties helloConfigurationProperties;

    public String hello() {
        return "%s %s".formatted(helloConfigurationProperties.getMessage(),
                LocalDateTime.now());
    }
}
