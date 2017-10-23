package st.validator;

import st.dto.UserRegistration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PasswordValidator implements ConstraintValidator<PasswordValid, Object> {
    @Override
    public void initialize(PasswordValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UserRegistration userRegistration = (UserRegistration) value;
        return Objects.equals(userRegistration.getPassword(), userRegistration.getMatchingPassword());
    }
}
