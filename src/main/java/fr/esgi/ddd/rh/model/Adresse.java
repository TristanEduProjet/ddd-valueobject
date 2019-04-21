package fr.esgi.ddd.rh.model;

import fr.esgi.ddd.rh.commun.utility.BeanValidator;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
@Data
public class Adresse {
    @Positive
    private final /*short*/int number;

    @NonNull @NotNull @NotEmpty
    private final String street, cp, city;

    public Adresse(final short number, @NonNull final String street, @NonNull final String cp, @NonNull final String city) throws ValidationException, NullPointerException {
        this((int)number, street, cp, city);
    }

    public Adresse(final int number, @NonNull final String street, @NonNull final String cp, @NonNull final String city) throws ValidationException, NullPointerException {
        this.number = number;
        this.street = street;
        this.cp = cp;
        this.city = city;
        BeanValidator.getInstance().isValid(this);
    }
}
