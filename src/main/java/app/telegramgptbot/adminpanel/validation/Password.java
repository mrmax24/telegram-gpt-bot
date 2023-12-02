package app.telegramgptbot.adminpanel.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "must contain at least one lowercase letter," +
            " one uppercase letter, one digit, and one special character (@_#$%^&+=)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
