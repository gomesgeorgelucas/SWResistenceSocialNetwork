package org.george.swresistencesocialnetwork.service;

import org.assertj.core.api.Assertions;
import org.george.swresistencesocialnetwork.dto.TradeDTO;
import org.george.swresistencesocialnetwork.exception.InvalidListException;
import org.george.swresistencesocialnetwork.exception.InvalidRequestException;
import org.george.swresistencesocialnetwork.exception.TradeBlockedException;
import org.george.swresistencesocialnetwork.mappers.ItemMapper;
import org.george.swresistencesocialnetwork.utils.InventoryUtil;
import org.george.swresistencesocialnetwork.utils.RebelUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TradeServiceTest {

    @InjectMocks
    TradeService tradeService;

    @Mock
    RebelService rebelService;
    @Mock
    ReportService reportService;

    @Test
    void shouldFailDuplicateId() {
        Assertions.assertThatThrownBy(() -> {
            tradeService.tryTrade(TradeDTO.builder()
                            .firstRebelId(1L)
                            .secondRebelId(1L)
                    .build());
        }).isInstanceOf(InvalidRequestException.class);
    }

    @Test
    void shouldFailTraitorTrading() {
        Mockito.when(reportService.isBlocked(1L)).thenReturn(true);
        Assertions.assertThatThrownBy(() -> {
            tradeService.tryTrade(TradeDTO.builder()
                    .firstRebelId(1L)
                    .secondRebelId(2L)
                    .build());
        }).isInstanceOf(TradeBlockedException.class);
    }

    @Test
    void shouldFailWhenListInvalid() {
        Mockito.when(rebelService.getRebel(1L)).thenReturn(RebelUtil.createRebelModel(1L));
        Mockito.when(rebelService.getRebel(2L)).thenReturn(RebelUtil.createRebelModel(2L));

        Assertions.assertThatThrownBy(() -> {
            tradeService.tryTrade(TradeDTO.builder()
                            .firstRebelId(1L)
                            .secondRebelId(2L)
                            .firstRebelItems(ItemMapper.itemTypeEnumToItemDTO(InventoryUtil.createInventory()))
                            .secondRebelItems(ItemMapper.itemTypeEnumToItemDTO(InventoryUtil.createInventory()))
                    .build());
        }).isInstanceOf(InvalidListException.class);
    }

    @Test
    void calculatePointsCorrectly() {
        Assertions.assertThat(tradeService.getPoints(ItemMapper.itemTypeEnumToItemDTO(InventoryUtil.createInventory()))).isEqualTo(13);
    }
}