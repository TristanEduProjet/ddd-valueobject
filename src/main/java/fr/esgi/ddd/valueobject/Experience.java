package fr.esgi.ddd.valueobject;

import fr.esgi.ddd.Technology;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Builder
@Data
public class Experience implements Comparable<Experience> {
    @PositiveOrZero
    private final int nbExp;

    @NonNull @NotNull
    private final Technology techno;

    public Experience(final int nbExp, @NonNull final Technology techno) throws NullPointerException, ValidationException {
        this.nbExp = nbExp;
        this.techno = techno;
        BeanValidator.getInstance().isValid(this);
    }

    @Override
    public int compareTo(final Experience o) {
        if(this.techno.equals(o.techno))
            return this.nbExp - o.nbExp;
        else
            return Integer.MIN_VALUE;
    }
}
