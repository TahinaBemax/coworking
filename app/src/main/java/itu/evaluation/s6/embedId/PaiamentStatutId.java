package itu.evaluation.s6.embedId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class PaiamentStatutId implements Serializable {
    @Column(name = "paiement_id", nullable = false)
    private Integer paiementId;
    @Column(name = "code_statut", nullable = false)
    private String codeStatut;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaiamentStatutId that = (PaiamentStatutId) o;
        return Objects.equals(paiementId, that.paiementId) && Objects.equals(codeStatut, that.codeStatut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paiementId, codeStatut);
    }
}
