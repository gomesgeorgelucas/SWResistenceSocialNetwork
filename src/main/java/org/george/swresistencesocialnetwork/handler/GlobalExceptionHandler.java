package org.george.swresistencesocialnetwork.handler;

import org.george.swresistencesocialnetwork.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

record Error(
        String message,
        String description
) {}

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidListException.class, })
    public ResponseEntity<Error> handleInvalidList(){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("Invalid list.", "Error"));
    }
    @ExceptionHandler({InvalidRequestException.class, })
    public ResponseEntity<Error> handleInvalidRequest(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Invalid Request.", "Error"));
    }

    @ExceptionHandler({MismatchedTradeException.class, })
    public ResponseEntity<Error> handleMismatchedTrade(){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Mismatched trade. Check points table.", "Access denied"));
    }

    @ExceptionHandler({RebelNotFoundException.class, })
    public ResponseEntity<Error> handleRebelNotFound(){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("Rebel not found.", "Access denied"));
    }

    @ExceptionHandler({TradeBlockedException.class, })
    public ResponseEntity<Error> handleTradeBlocked(){
        return  ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new Error("Trade not available.", "Access denied"));
    }

    @ExceptionHandler({UnknownLocationException.class, })
    public ResponseEntity<Error> handleUnknownLocation(){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Unknown location. Check maps.", "Access denied"));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, })
    public ResponseEntity<Error> handleHttpMessageNotReadable(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Invalid request. Malformed JSON.", "Error"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
