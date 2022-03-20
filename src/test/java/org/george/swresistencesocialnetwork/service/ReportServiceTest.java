package org.george.swresistencesocialnetwork.service;

import org.assertj.core.api.Assertions;
import org.george.swresistencesocialnetwork.dto.ReportDTO;
import org.george.swresistencesocialnetwork.exception.InvalidReportException;
import org.george.swresistencesocialnetwork.exception.InvalidRequestException;
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

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @InjectMocks
    ReportService reportService;

    @Mock
    ReportRepository reportRepository;

    @Mock
    RebelRepository rebelRepository;

    @Test
    void shouldFailWhenNullRequest() {
        Assertions.assertThatThrownBy(() -> {
            reportService.report(null);
        }).isInstanceOf(InvalidRequestException.class);
    }

    @Test
    void shouldFailWhenSuspectEqualsAccuser() {
        Assertions.assertThatThrownBy(() -> {
            reportService.report(ReportDTO.builder()
                    .suspectId(1L)
                    .accuserId(1L)
                    .build());
        }).isInstanceOf(InvalidReportException.class);
    }

    @Test
    void shouldFailWhenSuspectOrAccuserNotFound() {
        Mockito.when(rebelRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(rebelRepository.findById(2L)).thenReturn(Optional.of(RebelUtil.createRebelModel(2L)));
        Assertions.assertThatThrownBy(() -> {
            reportService.report(ReportDTO.builder()
                            .suspectId(1L)
                            .accuserId(2L)
                    .build());
        }).isInstanceOf(InvalidReportException.class);

        Mockito.when(rebelRepository.findById(2L)).thenReturn(Optional.empty());
        Mockito.when(rebelRepository.findById(1L)).thenReturn(Optional.of(RebelUtil.createRebelModel(1L)));
        Assertions.assertThatThrownBy(() -> {
            reportService.report(ReportDTO.builder()
                    .suspectId(1L)
                    .accuserId(2L)
                    .build());
        }).isInstanceOf(InvalidReportException.class);
    }

    @Test
    void traitorCannotBeAccuser() {
        Mockito.when(rebelRepository.findById(1L)).thenReturn(Optional.of(RebelUtil.createRebelModel(1L)));
        //Mockito.when(rebelRepository.findById(2L)).thenReturn(Optional.of(RebelUtil.createRebelModel(2L)));
        Mockito.when(reportRepository.getById(1L)).thenReturn(ReportUtil.createTraitor(1L));
        Assertions.assertThatThrownBy(() -> {
            reportService.report(ReportDTO.builder()
                            .suspectId(1L)
                            .accuserId(2L)
                    .build());
        }).isInstanceOf(InvalidReportException.class);
    }

    /*-----------------DATA-----------------*/

    @Test
    void duplicateReportShouldFail() {

    }

    void lessThanThreeAccusationsIsNotBlocked() {}
    void threeOrMoreAccusationsBlocksRebel() {}
}
