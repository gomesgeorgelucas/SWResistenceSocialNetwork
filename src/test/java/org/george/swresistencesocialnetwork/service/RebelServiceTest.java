package org.george.swresistencesocialnetwork.service;


import org.george.swresistencesocialnetwork.dto.LocationDTO;
import org.george.swresistencesocialnetwork.exception.InvalidListException;
import org.george.swresistencesocialnetwork.exception.InvalidRequestException;
import org.george.swresistencesocialnetwork.exception.RebelNotFoundException;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.repository.RebelRepository;
import org.george.swresistencesocialnetwork.utils.InventoryUtil;
import org.george.swresistencesocialnetwork.utils.LocationUtil;
import org.george.swresistencesocialnetwork.utils.RebelUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RebelServiceTest {

    @InjectMocks
    RebelService rebelService;
    @Mock
    RebelRepository rebelRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test should pass when rebel is not null")
    void shouldFailWhenAddNullRebel() {
        Assertions.assertThatThrownBy(() -> {
            rebelService.addRebel(null);
        }).isInstanceOf(InvalidRequestException.class);
    }

    @Test
    @DisplayName("Test should pass when rebel has null id")
    void shouldFailWhenAddRebelWithId() {
        Assertions.assertThatThrownBy(() -> {
            rebelService.addRebel(RebelUtil.createRebelModel(1L));
        }).isInstanceOf(InvalidRequestException.class);
    }

    @Test
    @DisplayName("Test should pass when rebel inventory")
    void shouldFailWhenAddRebelWithNullInventory() {
        Assertions.assertThatThrownBy(() -> {
            RebelModel invalidRebel = RebelUtil.createRebelModel();
            invalidRebel.setInventory(null);
            rebelService.addRebel(invalidRebel);
        }).isInstanceOf(InvalidRequestException.class);
    }

    @Test
    void shouldFailWhenRebelIdNullOrInvalid() {
        Assertions.assertThatThrownBy(() -> {
            rebelService.updateLocation(null, LocationUtil.createLocation());
        }).isInstanceOf(InvalidRequestException.class);

        Assertions.assertThatThrownBy(() -> {
            rebelService.updateLocation(-1L, LocationUtil.createLocation());
        }).isInstanceOf(InvalidRequestException.class);
    }

    @Test
    void shouldFailWhenLocationNullOrInvalid() {
        Assertions.assertThatThrownBy(() -> {
            rebelService.updateLocation(1L, null);
        }).isInstanceOf(InvalidRequestException.class);

        Assertions.assertThatThrownBy(() -> {
            LocationDTO invalidLocation = LocationUtil.createLocation();
            invalidLocation.setBase(null);
            rebelService.updateLocation(1L, invalidLocation);
        }).isInstanceOf(InvalidRequestException.class);

        Assertions.assertThatThrownBy(() -> {
            LocationDTO invalidLocation = LocationUtil.createLocation();
            invalidLocation.setLatitude(-91.00);
            rebelService.updateLocation(1L, invalidLocation);
        }).isInstanceOf(InvalidRequestException.class);

        Assertions.assertThatThrownBy(() -> {
            LocationDTO invalidLocation = LocationUtil.createLocation();
            invalidLocation.setLatitude(92.00);
            rebelService.updateLocation(1L, invalidLocation);
        }).isInstanceOf(InvalidRequestException.class);

        Assertions.assertThatThrownBy(() -> {
            LocationDTO invalidLocation = LocationUtil.createLocation();
            invalidLocation.setLongitude(-181.00);
            rebelService.updateLocation(1L, invalidLocation);
        }).isInstanceOf(InvalidRequestException.class);

        Assertions.assertThatThrownBy(() -> {
            LocationDTO invalidLocation = LocationUtil.createLocation();
            invalidLocation.setLongitude(181.00);
            rebelService.updateLocation(1L, invalidLocation);
        }).isInstanceOf(InvalidRequestException.class);
    }

    @Test
    void shouldFailWhenRebelToUpdateLocationNotFound() {
        Mockito.when(rebelRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> {
            rebelService.updateLocation(1L, LocationUtil.createLocation());
        }).isInstanceOf(RebelNotFoundException.class);
    }

    @Test
    void shouldFailWhenGetInvalidRebel() {
        Assertions.assertThatThrownBy(() -> {
            rebelService.getRebel(null);
        }).isInstanceOf(InvalidRequestException.class);

        Assertions.assertThatThrownBy(() -> {
            rebelService.getRebel(-1L);
        }).isInstanceOf(InvalidRequestException.class);

        Mockito.when(rebelRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> {
            rebelService.getRebel(1L);
        }).isInstanceOf(RebelNotFoundException.class);
    }

    @Test
    void shouldFailWhenInvalidInventory() {
        Mockito.when(rebelRepository.findById(1L)).thenReturn(Optional.of(RebelUtil.createRebelModel(1L)));

        Assertions.assertThatThrownBy(() -> {
            rebelService.updateInventory(1L, null, Collections.emptyList());
        }).isInstanceOf(InvalidRequestException.class);

        Assertions.assertThatThrownBy(() -> {
            rebelService.updateInventory(1L, Collections.emptyList(), null);
        }).isInstanceOf(InvalidRequestException.class);

        Assertions.assertThatThrownBy(() -> {
            rebelService.updateInventory(1L, Collections.emptyList(), InventoryUtil.createInventory());
        }).isInstanceOf(InvalidListException.class);

        Assertions.assertThatThrownBy(() -> {
            rebelService.updateInventory(1L, InventoryUtil.createInventory(), Collections.emptyList());
        }).isInstanceOf(InvalidListException.class);
    }

    /* -----------------DATA-------------------*/
    @Test
    void shouldPassWhenRebelNewLocationEqualsLocation() {
        RebelModel oldRebel = RebelUtil.createRebelModel(1L);
        Mockito.when(rebelRepository.findById(1L)).thenReturn(Optional.of(oldRebel));
        LocationDTO newLocation = LocationUtil.createLocation();
        RebelModel newRebel = oldRebel;
        newRebel.setLatitude(newLocation.getLatitude());
        newRebel.setLongitude(newLocation.getLongitude());
        newRebel.setBase(newLocation.getBase());
        Mockito.when(rebelRepository.save(newRebel)).thenReturn(newRebel);
        Assertions.assertThat(rebelService.updateLocation(1L, newLocation)).isEqualTo(newRebel);
    }

    @Test
    void testUpdateInventory() {

    }
}
