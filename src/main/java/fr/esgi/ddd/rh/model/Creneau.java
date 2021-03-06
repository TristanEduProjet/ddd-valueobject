package fr.esgi.ddd.rh.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

@Builder
@Data
public class Creneau implements Serializable {
    @NonNull @NotNull
    private final LocalDateTime start;

    @NonNull @NotNull
    private final LocalDateTime end;

    public Creneau(@NonNull final LocalDateTime start, @NonNull final LocalDateTime end) throws NullPointerException, ValidationException {
        this.start = start;
        this.end = end;
        if(start.isAfter(end) || start.isEqual(end))
            throw new ValidationException("Start must be before the end");
    }

    public Creneau(@NonNull final LocalDateTime from, @NonNull final Duration duration) throws NullPointerException, ValidationException {
        this(from, from.plus(duration));
    }
}
