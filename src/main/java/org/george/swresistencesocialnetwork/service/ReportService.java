package org.george.swresistencesocialnetwork.service;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.ReportDTO;
import org.george.swresistencesocialnetwork.exception.InvalidReportException;
import org.george.swresistencesocialnetwork.exception.InvalidRequestException;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.model.ReportModel;
import org.george.swresistencesocialnetwork.repository.RebelRepository;
import org.george.swresistencesocialnetwork.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReportService {
    final ReportRepository reportRepository;
    final RebelRepository rebelRepository;

    public ReportModel report(ReportDTO reportDTO) {
        if (reportDTO == null) {
            throw new InvalidRequestException();
        }

        if (Objects.equals(reportDTO.getSuspectId(), reportDTO.getAccuserId())) {
            throw new InvalidReportException();
        }

        Optional<RebelModel> optionalSuspect = rebelRepository.findById(reportDTO.getSuspectId());
        Optional<RebelModel> optionalAccuser = rebelRepository.findById(reportDTO.getAccuserId());

        if (optionalSuspect.isEmpty() || optionalAccuser.isEmpty()) {
            throw new InvalidReportException();
        }

        RebelModel suspect = optionalSuspect.get();
        RebelModel accuser = optionalAccuser.get();

        if (isBlocked(accuser.getId())) {
            throw new InvalidReportException();
        }

        Optional<ReportModel> optional = reportRepository.findById(suspect.getId());

        ReportModel report;

        if (optional.isEmpty()) {
            report = ReportModel.builder()
                    .suspectId(suspect.getId())
                    .accusers(new HashSet<>())
                    .build();
        } else {
            report = optional.get();
        }

        report.getAccusers().add(accuser.getId());

        return reportRepository.save(report);
    }

    public boolean isBlocked(Long id) {
        Optional<ReportModel> optional = reportRepository.findById(id);

        ReportModel report;

        if (optional.isPresent()) {
            report = optional.get();
        } else {
            return false;
        }

        return report.getAccusers().size() >= 3;
    }
}
