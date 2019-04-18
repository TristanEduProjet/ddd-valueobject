package fr.esgi.ddd.entity;

import fr.esgi.ddd.InvalidStateException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

//@Builder
@Data
public class Entretien implements Serializable {
    @NonNull @NotNull
    private final UUID id = UUID.randomUUID();

    @NonNull @NotNull @Setter(AccessLevel.PACKAGE) //for testing
    private EntretienState state = EntretienState.WAITING;

    @NonNull @NotNull @Setter(AccessLevel.NONE)
    private Optional<String> reasonCancel = Optional.empty();

    @NonNull @NotNull Object creneau;
    @NonNull @NotNull Object recruteur;
    @NonNull @NotNull Object candidat;

    public Entretien(@NonNull final Object creneau, @NonNull final Object recruteur, @NonNull final Object candidat) throws NullPointerException, ValidationException {
        this.creneau = creneau;
        this.recruteur = recruteur;
        this.candidat = candidat;
    }

    public void confirm() throws InvalidStateException {
        if(this.state == EntretienState.WAITING)
            this.state = EntretienState.CONFIRMED;
        else
            throw new InvalidStateException("The actual state is "+this.state);
    }

    public void cancel(@NonNull final String reason) throws InvalidStateException {
        if(this.state != EntretienState.CANCELED) {
            this.state = EntretienState.CANCELED;
            this.reasonCancel = Optional.of(reason);
        } else
            throw new InvalidStateException("Already canceled");
    }

    public enum EntretienState {
        WAITING,
        CONFIRMED,
        CANCELED
    }
}
