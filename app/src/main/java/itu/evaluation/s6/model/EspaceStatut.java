package itu.evaluation.s6.model;

import itu.evaluation.s6.embedId.EspaceStatutId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "statut_espace")
public class EspaceStatut {
    @EmbeddedId
    EspaceStatutId id;

    @NotNull
    LocalDateTime updatedAt;
}
