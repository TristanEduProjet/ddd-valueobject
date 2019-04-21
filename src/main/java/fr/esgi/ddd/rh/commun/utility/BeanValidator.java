package fr.esgi.ddd.rh.commun.utility;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Synchronized;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * Utility class for simplifying validation in ValueObjects
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanValidator {
    @NonNull @Getter
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private static BeanValidator beanValidator;

    @Synchronized
    public static BeanValidator getInstance() {
        if(beanValidator == null) beanValidator = new BeanValidator();
        return beanValidator;
    }

    public <T> void isValid(final @NonNull T obj) throws ValidationException {
        final Set<ConstraintViolation<T>> violations = this.validator.validate(obj);
        violations.stream().map(ConstraintViolation::getMessage)
                           .reduce((i, u) -> String.join(" ", i, "and", u))
                           .ifPresent(s -> { throw new ValidationException(s); });
    }
}
