package name.bouknecht.mywebapp.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Size;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Size(message = "{name.bouknecht.mywebapp.annotation.MaxLength.message}")
@Constraint(validatedBy = {})
public @interface MaxLength {
    String message() default "not applicable";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @OverridesAttribute(constraint = Size.class, name = "max")
    int value();
}
