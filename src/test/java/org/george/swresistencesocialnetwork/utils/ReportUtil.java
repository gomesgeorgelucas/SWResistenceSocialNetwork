package org.george.swresistencesocialnetwork.utils;

import org.george.swresistencesocialnetwork.model.ReportModel;

import java.util.*;

public class ReportUtil {
    public static List<ReportModel> createTraitorReports() {
        List<ReportModel> reports = new ArrayList<>();
        reports.add(createTraitor(1L));
        reports.add(createTraitor(5L));
        reports.add(createTraitor(9L));
        return reports;
    }

    public static ReportModel createSuspect(Long id) {
        Set<Long> accusers = new HashSet<>();
        return ReportModel.builder()
                .suspectId(id)
                .accusers(accusers)
                .build();
    }

    public static ReportModel createAccusation(Long suspectId, Long accuserId) {
        return ReportModel.builder()
                .suspectId(suspectId)
                .build();
    }

    public static ReportModel createTraitor(Long id) {;
        Set<Long> accusers = new HashSet<>();
        accusers.add(2L);
        accusers.add(3L);
        accusers.add(4L);
        return ReportModel.builder()
                .suspectId(id)
                .accusers(accusers)
                .build();
    }
}
