package training.employees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ControllerAdvice
public class EmployeeControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException e) {
        ProblemDetail detail = ProblemDetail
                .forStatus(HttpStatus.BAD_REQUEST);
        detail.setTitle("Invalid input");
        detail.setDetail(e.getMessage());

        detail.setProperty("exception-id", UUID.randomUUID().toString());
        detail.setProperty("error-time", LocalDateTime.now().toString());
        // Secure coding sértés! Nem mehet ki exception API-n
//        detail.setProperty("exception-class", e.getClass().getName());

        return detail;
    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException e) {
        ProblemDetail detail = ProblemDetail
                .forStatus(HttpStatus.NOT_FOUND);
        detail.setTitle("Nof found");
        detail.setDetail(e.getMessage());
        return detail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ProblemDetail detail = ProblemDetail
                .forStatus(HttpStatus.BAD_REQUEST);
        detail.setTitle("Invalid input");
        List<Violation> violations = e
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new Violation(fe.getField(), fe.getDefaultMessage()))
                .toList();
        detail.setProperty("violations", violations);
        return detail;
    }
}
