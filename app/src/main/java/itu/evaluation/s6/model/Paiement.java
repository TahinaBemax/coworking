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
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paiement_id")
    Integer id;

    @NotNull(message = "{champ.notNull}")
    @Column(name = "date_paiement")
    LocalDate DatePaiement;

    @PositiveOrZero(message = "{prix.positiveOrZero}")
    @Column(name = "montant_paye")
    BigDecimal montantPaye;

    @NotNull(message = "{champ.notNull}")
    @NotBlank(message = "{champ.notNull}")
    @Column(name = "ref_paiement")
    String refPaiement;

    @Column(name = "validated_at")
    LocalDateTime validatedAt;

    @NotNull(message = "{champ.notNull}")
    @Column(name = "admin_username")
    String adminUsername;

    @ManyToOne()
    @JoinColumn(name = "reservation_id")
    @NotNull(message = "{champ.notNull}")
    Reservation reservation;

    @Transient
    StatutPaiement statutPaiement;
}
