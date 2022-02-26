package org.george.swresistencesocialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swapi")
@RequiredArgsConstructor
public class RebelController {
    public ResponseEntity<String> getRebels() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
