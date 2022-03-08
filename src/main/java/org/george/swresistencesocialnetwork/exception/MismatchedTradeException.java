package org.george.swresistencesocialnetwork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MismatchedTradeException extends RuntimeException{
    public MismatchedTradeException() {
        super("Mismatched trade. Check points table.");
    }
}
