package training.employees;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SalaryConverter implements Converter<String, Salary> {

    @Override
    public Salary convert(String source) {
        return new Salary(Integer.parseInt(source));
    }

}
