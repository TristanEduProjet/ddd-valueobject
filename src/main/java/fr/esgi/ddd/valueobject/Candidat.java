package fr.esgi.ddd.valueobject;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
public class Candidat implements Serializable {
    @NonNull @NotNull @NotEmpty
    private final String name;

    @NonNull @NotNull
    private final Profil profil;

    public Candidat(@NonNull final String name, @NonNull final Profil profil) throws ValidationException {
        this.name = name;
        this.profil = profil;
        BeanValidator.getInstance().isValid(this);
    }
}
