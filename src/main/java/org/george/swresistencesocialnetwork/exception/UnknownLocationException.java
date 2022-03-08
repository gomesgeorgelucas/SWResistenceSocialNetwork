package org.george.swresistencesocialnetwork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnknownLocationException extends RuntimeException{
    public UnknownLocationException() {
        super("Unknown location. Check maps.");
    }
}
