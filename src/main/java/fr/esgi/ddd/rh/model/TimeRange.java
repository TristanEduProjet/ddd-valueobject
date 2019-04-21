package fr.esgi.ddd.rh.model;

import lombok.Data;
import lombok.NonNull;

import javax.validation.ValidationException;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class TimeRange {
    @NonNull private final LocalDateTime start, end;

    public TimeRange(@NonNull final LocalDateTime start, @NonNull final LocalDateTime end) throws ValidationException {
        this.start = start;
        this.end = end;
        if(!this.end.isAfter(this.start))
            throw new ValidationException("Start must be before the end");
    }

    public TimeRange(@NonNull final LocalDateTime start, @NonNull final Duration duration) throws ValidationException {
        this(start, start.plus(duration));
    }

    public boolean overlaps(@NonNull final TimeRange other) {
        return !(   (this.end.isBefore(other.start) && this.end.isBefore(other.end))
                 || (this.start.isAfter(other.start) && this.start.isAfter(other.end)));
    }

    public boolean isIncludeIn(@NonNull final TimeRange other) {
        return (this.start.isEqual(other.start) || this.start.isAfter(other.start))
            && (this.end.isEqual(other.end) || this.end.isBefore(other.end));
    }

    public Duration getDuration() {
        return Duration.between(start, end); //checks already did
    }
}
