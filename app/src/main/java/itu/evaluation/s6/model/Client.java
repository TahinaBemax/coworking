package itu.evaluation.s6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Entity
@Setter
@Getter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    Integer id;

    @Column(name = "numero_tel", nullable = false, unique = true)
    @NotNull
    //Pattern(regexp = "^(\\+261|0)(\\d{8})$", message = "Numéro de téléphone malgache invalide")
    String numeroTel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

}
