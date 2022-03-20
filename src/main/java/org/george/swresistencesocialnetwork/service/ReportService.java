package org.george.swresistencesocialnetwork.service;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.ReportDTO;
import org.george.swresistencesocialnetwork.exception.InvalidReportException;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.model.ReportModel;
import org.george.swresistencesocialnetwork.repository.RebelRepository;
import org.george.swresistencesocialnetwork.repository.ReportRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ReportService {
    final ReportRepository reportRepository;
    final RebelRepository rebelRepository;

    public ReportModel report(ReportDTO reportDTO) {

        RebelModel suspect = rebelRepository.getById(reportDTO.getSuspectId());
        RebelModel accuser = rebelRepository.getById(reportDTO.getAccuserId());

        if (Objects.equals(suspect.getId(), accuser.getId())) {
            throw new InvalidReportException();
        }

        ReportModel report;

        if (reportRepository.existsById(suspect.getId())) {
            report = reportRepository.findById(suspect.getId()).get();

        } else {
            report = new ReportModel();
            report.setSuspectId(suspect.getId());
        }

        report.getAccusers().add(accuser.getId());

        return reportRepository.save(report);
    }

    public boolean isBlocked(Long id) {
        return reportRepository.getById(id).getAccusers().size() >= 3;
    }
}
