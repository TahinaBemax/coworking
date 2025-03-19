package itu.evaluation.s6.embedId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class OptionReservationId implements Serializable {
    @Column(name = "reservation_id", nullable = false)
    private Integer reservationId;
    @Column(name = "code_option", nullable = false)
    private String codeOption;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionReservationId that = (OptionReservationId) o;
        return Objects.equals(reservationId, that.reservationId) && Objects.equals(codeOption, that.codeOption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, codeOption);
    }
}
