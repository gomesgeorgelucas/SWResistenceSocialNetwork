package org.george.swresistencesocialnetwork.controller;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.RecordDTO;
import org.george.swresistencesocialnetwork.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/swapi/records")
@AllArgsConstructor
public class RecordViewController {
    @Autowired
    final RecordService recordService;

    @GetMapping("/showRecords")
    public String showRecords(Model model) {
        RecordDTO records = recordService.getRecords();
        model.addAttribute("records", records);
        return "showRecords";
    }
}
