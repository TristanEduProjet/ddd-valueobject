package fr.esgi.ddd.valueobject;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
public class Profil implements Serializable {
    @NotNull private final Set<Experience> experiences;

    public Profil(@NotNull final Set<Experience> experiences) throws NullPointerException {
        this.experiences = Collections.unmodifiableSet(experiences);
        if(this.experiences.contains(null))
            throw new NullPointerException("Null Experience object found");
    }

    public Profil(@NotNull final Experience... experiences) throws NullPointerException {
        this(new HashSet<>(Arrays.asList(experiences)));
    }

    //TODO: getTotalExp
    //TODO: p.343
}
