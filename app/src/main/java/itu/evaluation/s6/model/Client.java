package itu.evaluation.s6.model;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    Integer id;

    @Column(name = "numero_tel", nullable = false, unique = true)
    @NotNull()
    //Pattern(regexp = "^(\\+261|0)(\\d{8})$", message = "Numéro de téléphone malgache invalide")
    String numeroTel;
}
