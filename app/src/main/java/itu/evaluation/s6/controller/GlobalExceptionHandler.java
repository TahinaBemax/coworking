package itu.evaluation.s6.controller;

import itu.evaluation.s6.exception.ImportCsvException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> exceptionHandler(IOException e){
        IOException ioException = new IOException("Une erreur s'est produite lors de la lecture du fichier: " + e.getMessage());
        return new ResponseEntity<>(ioException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e){
        Exception Exception = new Exception("Une erreur interne s'est produite! \n" + e.getMessage());
        return new ResponseEntity<>(Exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ImportCsvException.class)
    public ResponseEntity<?> exceptionHandler(ImportCsvException e){
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
