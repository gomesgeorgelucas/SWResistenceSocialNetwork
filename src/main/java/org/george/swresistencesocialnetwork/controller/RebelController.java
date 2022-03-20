package org.george.swresistencesocialnetwork.controller;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.*;
import org.george.swresistencesocialnetwork.exception.InvalidRequestException;
import org.george.swresistencesocialnetwork.mappers.RebelMapper;
import org.george.swresistencesocialnetwork.service.RebelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/swapi")
@AllArgsConstructor
public class RebelController {
    @Autowired
    final RebelService rebelService;

    @PostMapping("/addRebel")
    public ResponseEntity<RebelDTO> addRebel(@Valid @RequestBody RebelDTO rebelDTO) {
        if (rebelDTO == null) {
            throw new InvalidRequestException();
        }

        try {
            return new ResponseEntity<>(
                    RebelMapper.rebelModelToRebelDTO(
                            rebelService.addRebel(
                                    RebelMapper.rebelDTOtoRebelModel(rebelDTO)
                            )
                    ), HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    RebelMapper.rebelModelToRebelDTO(
                            rebelService.addRebel(
                                    RebelMapper.rebelDTOtoRebelModel(rebelDTO)
                            )
                    ), HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/updateLocation/{id}")
    public ResponseEntity<RebelDTO> updateLocation(@PathVariable Long id, @Valid @RequestBody LocationDTO updateLocationDTO) {
        if (id == null || updateLocationDTO == null || id < 0) {
            throw new InvalidRequestException();
        }

        return new ResponseEntity<>(
                RebelMapper.rebelModelToRebelDTO(
                        rebelService.updateLocation(id, updateLocationDTO)
                ), HttpStatus.OK
        );
    }
}
