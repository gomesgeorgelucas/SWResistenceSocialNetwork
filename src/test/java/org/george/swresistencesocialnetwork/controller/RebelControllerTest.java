package org.george.swresistencesocialnetwork.controller;

import org.george.swresistencesocialnetwork.service.RebelService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RebelControllerTest {

    @InjectMocks
    RebelController rebelController;

    @Mock
    RebelService rebelService;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAddRebel() {

    }

    @Test
    void testUpdateLocation() {
    }
}
