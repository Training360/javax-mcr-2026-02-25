package training.employees;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NameValidator.class)
public @interface ValidName {
    String message() default "érvénytelen név";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String[] ignored() default {};
}
