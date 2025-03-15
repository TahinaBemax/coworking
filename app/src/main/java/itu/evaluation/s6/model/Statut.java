package itu.evaluation.s6.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Statut {
    @Id
    @Column(name = "code_statut", nullable = false, unique = true)
    @NotNull(message = "{champ.notNull}")
    String codeStatut;

    @NotNull(message = "{champ.notNull}")
    @NotBlank(message = "{champ.notNull}")
    String statut;
}
