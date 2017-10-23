package st.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static java.time.Duration.between;
import static java.time.LocalDate.now;

public class BorrowLengthValidator implements ConstraintValidator<BorrowLengthValid, Object> {
    private static final int MAX_DAYS = 30;
    private static final int MIN_DAYS = 1;

    @Override
    public void initialize(BorrowLengthValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        LocalDate till = (LocalDate) value;
        long length = between(now().atStartOfDay(), till.atStartOfDay()).toDays();
        return length <= MAX_DAYS && length >= MIN_DAYS;
    }
}
