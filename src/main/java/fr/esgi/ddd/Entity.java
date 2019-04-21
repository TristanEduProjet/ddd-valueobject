package fr.esgi.ddd;

import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.UUID;

@Getter
//@EqualsAndHashCode(callSuper=false, doNotUseGetters=true, of={"id"})
public abstract class Entity implements Serializable {
    private final UUID id = UUID.randomUUID();

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public final boolean equals(@NonNull final Object o) {
        //if(this == o) return true;
        if(!(o instanceof Entity))
            return false;
        else
            return this.id.equals(((Entity) o).id);
    }
}
