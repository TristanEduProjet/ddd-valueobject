package fr.esgi.ddd.rh.model;

import fr.esgi.ddd.rh.commun.utility.BeanValidator;
import lombok.Data;
import lombok.NonNull;

import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class ConsultantRecruteur implements Serializable {
    @NonNull @NotNull
    private final UUID id;

    @NonNull @NotNull @NotEmpty
    private final String name;

    @NonNull @NotNull
    private final Profil profil;

    //@NonNull @NotNull private final Adresse adresse;

    public ConsultantRecruteur(@NonNull final String name, @NonNull final Profil profil) throws ValidationException {
        this(UUID.randomUUID(), name, profil);
    }

    public ConsultantRecruteur(@NonNull final UUID id, @NonNull final String name, @NonNull final Profil profil) throws ValidationException {
        this.id = id;
        this.name = name;
        this.profil = profil;
        BeanValidator.getInstance().isValid(this);
    }

    public final boolean canTest(@NonNull final Candidat candidat) {
        final Map<Technology, Integer> RExp = this.profil.getExperiences().parallelStream().collect(Collectors.toMap(Experience::getTechno, Experience::getNbExp));
        final Map<Technology, Integer> CExp = candidat.getProfil().getExperiences().parallelStream().collect(Collectors.toMap(Experience::getTechno, Experience::getNbExp));
        final Set<Technology> exp = CExp.keySet();
        exp.retainAll(RExp.keySet());
        return exp.stream().mapToInt(t -> RExp.get(t) - CExp.get(t)).anyMatch(d -> d > 5);
    }
}
