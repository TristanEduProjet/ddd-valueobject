package fr.esgi.ddd.rh.use_case;

import fr.esgi.ddd.rh.commun.dto.ConsultantDTO;
import fr.esgi.ddd.rh.model.ConsultantRecruteur;
import fr.esgi.ddd.rh.model.TimeRange;
import lombok.NonNull;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RecruteurRepository {
    Optional<ConsultantDTO> getById(@NonNull final UUID id);

    Set<ConsultantDTO> getAvailablesByTimerange(@NonNull final TimeRange range);
}
