package org.george.swresistencesocialnetwork.service;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.model.ReportModel;
import org.george.swresistencesocialnetwork.repository.ReportRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportService {
    final ReportRepository reportRepository;

    public ReportModel report(RebelModel suspect, RebelModel accuser) {
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
        if (reportRepository.findBySuspectId(id).size() >= 3) {
            return true;
        }

        return false;
    }
}
