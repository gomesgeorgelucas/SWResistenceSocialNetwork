package org.george.swresistencesocialnetwork.controller;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.RebelDTO;
import org.george.swresistencesocialnetwork.service.ItemService;
import org.george.swresistencesocialnetwork.service.RebelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swapi")
@AllArgsConstructor
public class RebelController {

    RebelService rebelService;
    ItemService itemService;

    @PostMapping("/addRebel")
    public ResponseEntity<RebelDTO> addRebel(@RequestBody RebelDTO rebelDTO) {
        rebelService.addRebel(rebelDTO);
        return new ResponseEntity<>(rebelDTO, HttpStatus.CREATED);
    }
}
