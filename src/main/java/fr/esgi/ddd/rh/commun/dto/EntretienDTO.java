package fr.esgi.ddd.rh.commun.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Data
public class EntretienDTO implements Serializable {
    private UUID id;
    private CandidatDTO candidat;
    private ConsultantDTO recruteur;

}
