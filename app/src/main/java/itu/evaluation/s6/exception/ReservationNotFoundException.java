package itu.evaluation.s6.exception;

import lombok.Data;

@Data
public class ReservationNotFoundException extends Exception{
    String reference;
    String message;

    public ReservationNotFoundException(String reference) {
        super(String.format("Reference: %s not found", reference));
        this.reference = reference;
        this.message = super.getMessage();
    }

    public ReservationNotFoundException(String message, String reference, String message1) {
        super(message);
        this.reference = reference;
        this.message = message1;
    }
}
