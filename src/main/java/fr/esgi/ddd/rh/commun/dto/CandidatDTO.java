package fr.esgi.ddd.rh.commun.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Data
public class CandidatDTO implements Serializable {
    private final UUID id;
}
