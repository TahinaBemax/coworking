package itu.evaluation.s6.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class Heure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heure_id")
    Integer id;

    @Column(name = "heure_ouverture", nullable = false)
    @NotNull()
    LocalTime heureOuverture;

    @Column(name = "heure_fermeture", nullable = false)
    @NotNull()
    LocalTime heureFermeture;
}
