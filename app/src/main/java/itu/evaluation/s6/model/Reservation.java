package itu.evaluation.s6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    Integer id;

    @NotNull(message = "{champ.notNull}")
    @NotBlank(message = "{champ.notNull}")
    String reference;

    @Column(name = "annule_le")
    LocalDateTime annuleLe;

    @NotNull(message = "{champ.notNull}")
    LocalDate DateReservation;

    @NotNull(message = "{champ.notNull}")
    @Column(name = "heure_debut")
    LocalTime heureDebut;

    @NotNull(message = "{champ.notNull}")
    @Column(name = "heure_fin")
    LocalTime heureFin;

    @PositiveOrZero(message = "{prix.positiveOrZero}")
    @Column(name = "prix_espace")
    BigDecimal prixEspace;

    @OneToMany()
    @JoinColumn(name = "code_option")
    List<Option> options;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    @NotNull(message = "{champ.notNull}")
    Client client;

    @OneToOne()
    @JoinColumn(name = "espace_id")
    @NotNull(message = "{champ.notNull}")
    Espace espace;
}
