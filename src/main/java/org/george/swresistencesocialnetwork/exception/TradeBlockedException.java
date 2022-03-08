package org.george.swresistencesocialnetwork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class TradeBlockedException extends RuntimeException{
    public TradeBlockedException() {
        super("Trade not available.");
    }
}
