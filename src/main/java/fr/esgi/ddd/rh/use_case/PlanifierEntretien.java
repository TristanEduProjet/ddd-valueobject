package fr.esgi.ddd.rh.use_case;

import fr.esgi.ddd.rh.commun.dto.CandidatDTO;
import fr.esgi.ddd.rh.commun.dto.EntretienDTO;
import fr.esgi.ddd.rh.commun.errors.NoPossibilityFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class PlanifierEntretien {
    @NonNull private EntretienRepository repository;
    @NonNull private CandidatRepository candidatRepository;
    @NonNull private RecruteurRepository recruteurRepository;

    public EntretienDTO planifierEntretien(@NonNull final CandidatDTO candidat) throws NoPossibilityFoundException {
        //final Candidat candidatVo = Candidat.builder().id(con)
        return null;
    }
}
