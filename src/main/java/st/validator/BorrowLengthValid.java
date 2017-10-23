package st.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = BorrowLengthValidator.class)
@Documented
public @interface BorrowLengthValid {
    String message() default "valid borrow lengths are from 1 day to 30 day";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}