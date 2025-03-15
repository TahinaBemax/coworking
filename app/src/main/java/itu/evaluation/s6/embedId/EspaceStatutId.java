package itu.evaluation.s6.embedId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class EspaceStatutId implements Serializable {
    @Column(name = "espace_id", nullable = false)
    private Integer espaceId;
    @Column(name = "code_statut", nullable = false)
    private String codeStatut;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EspaceStatutId that = (EspaceStatutId) o;
        return Objects.equals(espaceId, that.espaceId) && Objects.equals(codeStatut, that.codeStatut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(espaceId, codeStatut);
    }
}
