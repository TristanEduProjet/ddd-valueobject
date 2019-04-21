package fr.esgi.ddd.rh.use_case;

import fr.esgi.ddd.rh.commun.dto.ConsultantDTO;
import fr.esgi.ddd.rh.model.TimeRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Singular;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruteurRepositoryImpl implements RecruteurRepository {
    @NonNull @Singular
    private Set<ConsultantDTO> consultants = Collections.emptySet();

    @Override
    public Optional<ConsultantDTO> getById(@NonNull final UUID id) {
        return this.consultants.stream().filter(c -> id.equals(c.getId())).findAny();
    }

    @Override
    public Set<ConsultantDTO> getAvailablesByTimerange(@NonNull final TimeRange range) {
        return null;
    }
}
