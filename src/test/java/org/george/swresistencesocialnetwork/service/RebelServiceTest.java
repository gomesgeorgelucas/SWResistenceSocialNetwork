package org.george.swresistencesocialnetwork.service;

import org.george.swresistencesocialnetwork.AbstractUnitTest;
import org.george.swresistencesocialnetwork.enums.BaseEnum;
import org.george.swresistencesocialnetwork.enums.ItemTypeEnum;
import org.george.swresistencesocialnetwork.mappers.RebelMapper;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.repository.RebelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

public class RebelServiceTest extends AbstractUnitTest {

    @InjectMocks
    RebelService rebelService;

    @Mock
    RebelRepository rebelRepository;

    @BeforeEach
    void setUp() {
        Mockito.when(rebelRepository.findById(createRebelModel().getId())).thenReturn(Optional.ofNullable(createRebelModel()));
    }

    void updateLocation() {}
    void getRebel() {}
    void updateInventory() {}
    void recoverRebelInventory() {}

    @Test
    void persistRebel() {
        rebelService.addRebel(RebelMapper.rebelModelToRebelDTO(createRebelModel()));
        Assertions.assertEquals(rebelRepository.existsById(1L), true);
    }

    static RebelModel createRebelModel() {
        return RebelModel.builder()
                .id(1L)
                .name("RebelTest")
                .age(18)
                .gender("Unknown")
                .latitude(0.0)
                .longitude(0.0)
                .base(BaseEnum.ATOLLON)
                .inventory(Arrays.asList(ItemTypeEnum.AMMO, ItemTypeEnum.FOOD, ItemTypeEnum.WATER, ItemTypeEnum.WEAPON))
                .build();
    }
}
