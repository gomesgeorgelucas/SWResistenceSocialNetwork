package org.george.swresistencesocialnetwork.service;

import org.george.swresistencesocialnetwork.repository.RebelRepository;
import org.george.swresistencesocialnetwork.repository.ReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RecordServiceTest {

    @InjectMocks
    RecordService recordService;

    @Mock
    RebelRepository rebelRepository;

    @Mock
    ReportRepository reportRepository;

    @Test
    void testCalculateTraitorsPercentage() {
    }

    @Test
    void getTraitorCount() {}

    @Test
    void calculateAverageResourcesPerRebel() {}

    @Test
    void calculateLostPointsDueToTraitors() {}

    @Test
    void getRecords() {
    }
}
