package itu.evaluation.s6.model;

import itu.evaluation.s6.enums.EspaceStatut;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;


@Entity
@Data
public class Espace {
    @Id
    @Column(name = "espace_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotNull(message = "{champ.notNull}")
    @Column(nullable = false, unique = true)
    String nom;

    @PositiveOrZero(message = "{prix.positiveOrZero}")
    @NotNull(message = "{champ.notNull}")
    BigDecimal prix;

    @Transient
    @Enumerated(EnumType.STRING)
    EspaceStatut status;

    public Espace() {
        this.status = EspaceStatut.LIBRE;
    }
}
