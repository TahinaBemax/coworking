package itu.evaluation.s6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_option")
    String id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "{option.notNull}")
    String option;

    @NotNull
    @PositiveOrZero(message = "Prix invalid")
    BigDecimal prix;
}
