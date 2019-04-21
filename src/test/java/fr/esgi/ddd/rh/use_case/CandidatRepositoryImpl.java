package fr.esgi.ddd.rh.use_case;

import fr.esgi.ddd.rh.commun.dto.CandidatDTO;
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
@AllArgsConstructor
@NoArgsConstructor
public class CandidatRepositoryImpl implements CandidatRepository {
    @NonNull @Singular()
    private Set<CandidatDTO> candidats = Collections.emptySet();

    @Override
    public Optional<CandidatDTO> getById(@NonNull final UUID id) {
        return candidats.stream().filter(c -> id.equals(c.getId())).findFirst();
    }
}
