package org.george.swresistencesocialnetwork.controller;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.RecordDTO;
import org.george.swresistencesocialnetwork.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swapi/records")
@AllArgsConstructor
public class RecordController {
    @Autowired
    final RecordService recordService;

    @GetMapping("/")
    public ResponseEntity<RecordDTO> getRecords() {
        if (recordService.getRecords() == null) {
            return new ResponseEntity<>(new RecordDTO(), HttpStatus.NOT_FOUND);
        }

        RecordDTO records = recordService.getRecords();

        return new ResponseEntity<>(records, HttpStatus.OK);
    }
}
