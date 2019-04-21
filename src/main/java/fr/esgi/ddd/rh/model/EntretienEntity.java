package fr.esgi.ddd.rh.model;

import fr.esgi.ddd.rh.commun.errors.InvalidStateException;
import lombok.AccessLevel;
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
public class EntretienEntity implements Serializable {
    @NonNull @NotNull
    private final UUID id = UUID.randomUUID();

    @NonNull @NotNull @Setter(AccessLevel.PACKAGE) //for testing
    private EntretienState state = EntretienState.WAITING;

    @NonNull @NotNull @Setter(AccessLevel.NONE)
    private Optional<String> reasonCancel = Optional.empty();

    @NonNull @NotNull TimeRange creneau;
    @NonNull @NotNull ConsultantRecruteur recruteur;
    @NonNull @NotNull Candidat candidat;

    public EntretienEntity(@NonNull final TimeRange creneau, @NonNull final ConsultantRecruteur recruteur, @NonNull final Candidat candidat) throws NullPointerException, ValidationException {
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
