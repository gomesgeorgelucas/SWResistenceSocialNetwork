package org.george.swresistencesocialnetwork.service;


import org.george.swresistencesocialnetwork.exception.InvalidRequestException;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class RebelServiceTest {

    RebelService rebelService;

    @BeforeEach
    void setUp() {
        rebelService = new RebelService(null);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test should pass when rebel is not null")
    void shouldFailWhenNullRebel() {
        Assertions.assertThatThrownBy(() -> {
            rebelService.addRebel(null);
        }).isInstanceOf(InvalidRequestException.class);
    }

    @Test
    @DisplayName("Test should pass when rebel has null id")
    void cannotAddRebelWithId() {

    }

    @Test
    void testUpdateLocation() {
    }

    @Test
    void testGetRebel() {
    }

    @Test
    void testUpdateInventory() {
    }
}
