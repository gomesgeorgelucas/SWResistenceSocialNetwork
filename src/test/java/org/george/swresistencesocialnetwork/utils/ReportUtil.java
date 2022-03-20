package org.george.swresistencesocialnetwork.utils;

import org.george.swresistencesocialnetwork.model.ReportModel;

import java.util.*;

public class ReportUtil {
    public static Collection<ReportModel> createReports() {
        Collection<ReportModel> reports = new ArrayList<>();
        reports.add(createReport(1L));
        reports.add(createReport(5L));
        reports.add(createReport(9L));
        return reports;
    }

    public static ReportModel createReport(Long id) {;
        Set<Long> accusers = new HashSet<>();
        accusers.add(id+1L);
        accusers.add(id+2L);
        accusers.add(id+3L);
        return ReportModel.builder()
                .suspectId(id)
                .accusers(accusers)
                .build();
    }
}
