package fr.esgi.ddd.rh.model;

import fr.esgi.ddd.rh.commun.utility.BeanValidator;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Builder
@Data
public class Candidat implements Serializable {
    @NonNull @NotNull
    private final UUID id;

    @NonNull @NotNull @NotEmpty
    private final String name;

    @NonNull @NotNull
    private final Profil profil;

    /*@NonNull @NotNull
    private final Adresse adress;*/

    public Candidat(@NonNull final UUID id, @NonNull final String name, @NonNull final Profil profil) throws ValidationException {
        this.id = id;
        this.name = name;
        this.profil = profil;
        BeanValidator.getInstance().isValid(this);
    }

    public Candidat(@NonNull final String name, @NonNull final Profil profil) throws ValidationException {
       this(UUID.randomUUID(), name, profil);
    }
}
