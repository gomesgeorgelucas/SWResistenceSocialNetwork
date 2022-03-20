package org.george.swresistencesocialnetwork.controller;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.TradeDTO;
import org.george.swresistencesocialnetwork.exception.InvalidRequestException;
import org.george.swresistencesocialnetwork.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/swapi")
@AllArgsConstructor
public class TradeController {

    @Autowired
    final TradeService tradeService;

    @PostMapping("/trade")
    public ResponseEntity<String> trade(@Valid @RequestBody TradeDTO tradeDTO) {
        if (tradeDTO == null) {
            throw new InvalidRequestException();
        }

        try {
            return new ResponseEntity<>(tradeService.trade(tradeDTO), HttpStatus.OK);
        } catch (Exception ignored) {}

        return new ResponseEntity<>(tradeService.trade(tradeDTO), HttpStatus.BAD_REQUEST);
    }
}
