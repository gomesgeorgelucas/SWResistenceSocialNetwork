package org.george.swresistencesocialnetwork.service;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.RecordDTO;
import org.george.swresistencesocialnetwork.enums.ItemTypeEnum;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.model.ReportModel;
import org.george.swresistencesocialnetwork.repository.RebelRepository;
import org.george.swresistencesocialnetwork.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Service
@AllArgsConstructor
public class RecordService {
    @Autowired
    final RebelRepository rebelRepository;
    @Autowired
    final ReportRepository reportRepository;

    public RecordDTO getRecords() {
        return RecordDTO.builder()
                .traitorsPercentage(calculateTraitorsPercentage())
                .rebelsPercentage(calculateRebelsPercentage())
                .averageResourcesPerRebel(calculateAverageResourcesPerRebel())
                .lostPointsDueToTraitors(calculateLostPointsDueToTraitors())
                .build();
    }

    private Double calculateTraitorsPercentage() {
        double numberOfRebels = rebelRepository.count();
        if (numberOfRebels == 0) {
            return 0.0;
        }

        double numberOfTraitors = getTraitorCount();

        if (numberOfTraitors == 0) {
            return 0.0;
        }

        double percentage = (numberOfTraitors / numberOfRebels) * 100.0;

        return percentage;
    }

    private Double calculateRebelsPercentage() {
        if (rebelRepository.count() == 0) {
            return 0.0;
        }

        return 100.0 - calculateTraitorsPercentage();
    }

    private Long getTraitorCount() {
        Collection<ReportModel> suspects = reportRepository.findAll();
        long numberOfTraitors = 0;
        for (ReportModel report : suspects) {
            if (report.getAccusers().size() >= 3) {
                numberOfTraitors++;
            }
        }

        return numberOfTraitors;
    }

    /*
    TODO - Change foreach to streams Collector
     */
    private Collection<Double> calculateAverageResourcesPerRebel() {
        double weaponCount = 0.0;
        double ammoCount = 0.0;
        double waterCount = 0.0;
        double foodCount = 0.0;

        long realRebels = rebelRepository.count() - getTraitorCount();

        if (realRebels == 0) {
            return Arrays.asList(0.0,0.0,0.0,0.0);
        }

        Collection<ReportModel> reports = reportRepository.findAll();
        Collection<RebelModel> traitors = new ArrayList<>();

        for (ReportModel report : reports) {
            if (report.getAccusers().size() >= 3) {
                traitors.add(rebelRepository.getById(report.getSuspectId()));
            }
        }

        Collection<RebelModel> rebels = rebelRepository.findAll();
        rebels.removeAll(traitors);

        for (RebelModel rebel : rebels) {
            for (ItemTypeEnum item : rebel.getInventory()) {
                if (item.ordinal() == 0) {
                    weaponCount++;
                }
                if (item.ordinal() == 1) {
                    ammoCount++;
                }
                if (item.ordinal() == 2) {
                    waterCount++;
                }
                if (item.ordinal() == 3) {
                    foodCount++;
                }
            }
        }

        Collection<Double> averageResourcesPerRebel = new ArrayList<>();
        averageResourcesPerRebel.add(weaponCount/realRebels);
        averageResourcesPerRebel.add(ammoCount/realRebels);
        averageResourcesPerRebel.add(waterCount/realRebels);
        averageResourcesPerRebel.add(foodCount/realRebels);

        return averageResourcesPerRebel;
    }

    private Long calculateLostPointsDueToTraitors() {
        long lostPoints = 0;

        Collection<ReportModel> reports = reportRepository.findAll();
        Collection<RebelModel> traitors = new ArrayList<>();

        for (ReportModel report : reports) {
            if (report.getAccusers().size() >= 3) {
                traitors.add(rebelRepository.getById(report.getSuspectId()));
            }
        }

        for (RebelModel rebel : traitors) {
            for (ItemTypeEnum item : rebel.getInventory()) {
                lostPoints += item.getValue();
            }
        }

        return lostPoints;
    }


}
