package org.george.swresistencesocialnetwork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RebelNotFoundException extends RuntimeException{
    public RebelNotFoundException() {
        super("Rebel not found.");
    }
}
