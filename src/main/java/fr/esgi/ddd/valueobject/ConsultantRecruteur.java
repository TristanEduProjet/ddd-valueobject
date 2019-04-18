package fr.esgi.ddd.valueobject;

import lombok.NonNull;

import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ConsultantRecruteur implements Serializable {
    @NonNull @NotNull @NotEmpty
    private final String name;

    @NonNull @NotNull
    private final Profil profil;

    //@NonNull @NotNull private final Adresse adresse;

    public ConsultantRecruteur(@NonNull final String name, @NonNull final Profil profil) throws ValidationException {
        this.name = name;
        this.profil = profil;
        BeanValidator.getInstance().isValid(this);
    }

    //TODO: canTest(candidat)
}
