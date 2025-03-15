package itu.evaluation.s6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    Integer id;

    @NotNull(message = "{champ.notNull}")
    @Column(unique = true, nullable = false)
    String username;

    @NotNull(message = "{champ.notNull}")
    @Column(nullable = false)
    String password;

}
