package itu.evaluation.s6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.annotations.Type;

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

    @Column(columnDefinition = "DECIMAL(15,2) CHECK (prix >= 0)")
    @PositiveOrZero(message = "{prix.positiveOrZero}")
    @NotNull(message = "{champ.notNull}")
    BigDecimal prix;

    @Transient
    @NotNull(message = "{champ.notNull}")
    EspaceStatut status;
}
