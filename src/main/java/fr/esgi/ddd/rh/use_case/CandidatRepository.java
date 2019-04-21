package fr.esgi.ddd.rh.use_case;

import fr.esgi.ddd.rh.commun.dto.CandidatDTO;
import lombok.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface CandidatRepository {
    Optional<CandidatDTO> getById(@NonNull final UUID id);
}
