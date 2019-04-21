package fr.esgi.ddd.rh.use_case;

import fr.esgi.ddd.rh.commun.dto.CandidatDTO;
import fr.esgi.ddd.rh.commun.dto.ConsultantDTO;
import fr.esgi.ddd.rh.commun.dto.EntretienDTO;
import lombok.NonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface EntretienRepository {
    Optional<EntretienDTO> getById(@NonNull final UUID id);

    Collection<EntretienDTO> getByCandidat(@NonNull final CandidatDTO candidat);
    Collection<EntretienDTO> getByCandidat(@NonNull final UUID id);

    Collection<EntretienDTO> getByRecruteur(@NonNull final ConsultantDTO recruteur);
    Collection<EntretienDTO> getByRecruteur(@NonNull final UUID id);
}
