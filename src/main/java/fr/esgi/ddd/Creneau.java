package fr.esgi.ddd;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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

    public Creneau(@NonNull final LocalDateTime start, @NonNull final LocalDateTime end) {
        this.start = start;
        this.end = end;
        if(start.isAfter(end) || start.isEqual(end))
            throw new ValidationException("Start must be before the end");
    }

    public Creneau(@NonNull final LocalDateTime from, @NonNull final Duration duration) {
        this(from, from.plus(duration));
    }
}
