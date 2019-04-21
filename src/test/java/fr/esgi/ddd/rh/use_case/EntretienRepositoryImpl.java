package fr.esgi.ddd.rh.use_case;

import fr.esgi.ddd.rh.commun.dto.CandidatDTO;
import fr.esgi.ddd.rh.commun.dto.ConsultantDTO;
import fr.esgi.ddd.rh.commun.dto.EntretienDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Singular;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntretienRepositoryImpl implements EntretienRepository {
    @NonNull @Singular
    private Set<EntretienDTO> entretiens = Collections.emptySet();

    @Override
    public Optional<EntretienDTO> getById(@NonNull final UUID id) {
        return this.entretiens.stream().filter(e -> id.equals(e.getId())).findAny();
    }

    @Override
    public Collection<EntretienDTO> getByCandidat(@NonNull final CandidatDTO candidat) {
        return this.entretiens.stream().filter(e -> candidat.equals(e.getCandidat())).collect(Collectors.toList());
    }

    @Override
    public Collection<EntretienDTO> getByCandidat(@NonNull final UUID id) {
        return this.entretiens.stream().filter(e -> id.equals(e.getCandidat().getId())).collect(Collectors.toList());
    }

    @Override
    public Collection<EntretienDTO> getByRecruteur(@NonNull final ConsultantDTO recruteur) {
        return this.entretiens.stream().filter(e -> recruteur.equals(e.getRecruteur())).collect(Collectors.toList());
    }

    @Override
    public Collection<EntretienDTO> getByRecruteur(@NonNull final UUID id) {
        return this.entretiens.stream().filter(e -> id.equals(e.getRecruteur().getId())).collect(Collectors.toList());
    }
}
