package fr.esgi.ddd.rh.use_case;

import fr.esgi.ddd.rh.commun.dto.CandidatDTO;
import fr.esgi.ddd.rh.commun.dto.ConsultantDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class PlanifierEntretienTest {
    @Test
    public void plannifier_avec_creneau_libre() {
        final CandidatRepository cr = CandidatRepositoryImpl.builder()
                .candidat(CandidatDTO.builder().id(UUID.randomUUID())/*TODO*/.build())
                .build();
        final RecruteurRepository rr = RecruteurRepositoryImpl.builder()
                .consultant(ConsultantDTO.builder().id(UUID.randomUUID())/*TODO*/.build())
                .build();
        final EntretienRepository er = new EntretienRepositoryImpl();
    }
}
