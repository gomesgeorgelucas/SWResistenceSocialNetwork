package org.george.swresistencesocialnetwork.controller;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.*;
import org.george.swresistencesocialnetwork.exception.InvalidRequestException;
import org.george.swresistencesocialnetwork.mappers.ItemMapper;
import org.george.swresistencesocialnetwork.mappers.RebelMapper;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.service.RebelService;
import org.george.swresistencesocialnetwork.service.ReportService;
import org.george.swresistencesocialnetwork.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/swapi")
@AllArgsConstructor
public class SWSocialController {
    @Autowired
    final RebelService rebelService;
    @Autowired
    final ReportService reportService;
    @Autowired
    final TradeService tradeService;

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

    @PostMapping("/reportSuspect")
    public ResponseEntity<ReportDTO> reportSuspect(@Valid @RequestBody ReportDTO reportDTO) {
        if (reportDTO == null) {
            throw new InvalidRequestException();
        }

        reportService.report(reportDTO);
        return new ResponseEntity<>(reportDTO, HttpStatus.OK);
    }

    @PostMapping("/trade")
    public ResponseEntity<@Valid TradeDTO> trade(@Valid @RequestBody TradeDTO tradeDTO) {
        if (tradeDTO == null) {
            throw new InvalidRequestException();
        }

        try {
            return new ResponseEntity<>(tradeService.trade(tradeDTO), HttpStatus.OK);
        } catch (Exception ignored) {}

        return new ResponseEntity<>(tradeService.trade(tradeDTO), HttpStatus.BAD_REQUEST);
    }
}
