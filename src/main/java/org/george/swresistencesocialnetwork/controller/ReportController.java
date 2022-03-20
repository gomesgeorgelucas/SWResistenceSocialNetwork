package org.george.swresistencesocialnetwork.controller;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.ReportDTO;
import org.george.swresistencesocialnetwork.exception.InvalidRequestException;
import org.george.swresistencesocialnetwork.service.ReportService;
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
public class ReportController {

    @Autowired
    final ReportService reportService;

    @PostMapping("/reportSuspect")
    public ResponseEntity<ReportDTO> reportSuspect(@Valid @RequestBody ReportDTO reportDTO) {
        if (reportDTO == null) {
            throw new InvalidRequestException();
        }

        reportService.report(reportDTO);
        return new ResponseEntity<>(reportDTO, HttpStatus.OK);
    }
}
