package itu.evaluation.s6.exception;

import lombok.Getter;

@Getter
public class PhoneNumberInvalidException extends Exception{
    private String phoneNumber;
    public PhoneNumberInvalidException(String phoneNumber) {
        super("Le numero de télephone "  + phoneNumber + "  est invalid");
        this.phoneNumber = phoneNumber;
    }
}
