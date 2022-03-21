package org.george.swresistencesocialnetwork.service;

import org.assertj.core.api.Assertions;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.repository.RebelRepository;
import org.george.swresistencesocialnetwork.repository.ReportRepository;
import org.george.swresistencesocialnetwork.utils.RebelUtil;
import org.george.swresistencesocialnetwork.utils.ReportUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RecordServiceTest {

    @InjectMocks
    RecordService recordService;

    @Mock
    RebelRepository rebelRepository;

    @Mock
    ReportRepository reportRepository;

    @Test
    void getRecords() {
        Mockito.when(rebelRepository.count()).thenReturn(10L);
        Mockito.when(reportRepository.findAll()).thenReturn(ReportUtil.createTraitorReports());
        Assertions.assertThat(recordService.calculateTraitorsPercentage()).isEqualTo(30.0);
        Assertions.assertThat(recordService.calculateRebelsPercentage()).isEqualTo(70.0);
        List<RebelModel> mockRebels = new ArrayList<>();

        for (int i = 0 ; i < 10; i++) {
            mockRebels.add(RebelUtil.createRebelModel((long) (i+1)));
        }

        Mockito.when(rebelRepository.getById(1L)).thenReturn(mockRebels.get(0));
        Mockito.when(rebelRepository.getById(5L)).thenReturn(mockRebels.get(4));
        Mockito.when(rebelRepository.getById(9L)).thenReturn(mockRebels.get(8));

        Mockito.when(rebelRepository.findAll()).thenReturn(mockRebels);
        Collection<Double> averages = new ArrayList<>();
        averages.add(1.0);
        averages.add(2.0);
        averages.add(1.0);
        averages.add(1.0);
        Assertions.assertThat(recordService.calculateAverageResourcesPerRebel()).isEqualTo(averages);
        Assertions.assertThat(recordService.calculateLostPointsDueToTraitors()).isEqualTo(39);
    }
}
