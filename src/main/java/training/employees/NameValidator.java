package training.employees;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class NameValidator implements ConstraintValidator<ValidName, String> {

    private String[] ignored;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (Arrays.stream(ignored).anyMatch(s::startsWith)) {
            return true;
        }
        return s != null && !s.isEmpty() && Character.isUpperCase(s.charAt(0));
    }

    @Override
    public void initialize(ValidName constraintAnnotation) {
        ignored = constraintAnnotation.ignored();
    }
}
