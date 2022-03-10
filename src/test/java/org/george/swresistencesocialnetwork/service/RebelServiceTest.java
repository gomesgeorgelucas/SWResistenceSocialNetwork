package org.george.swresistencesocialnetwork.service;

import org.george.swresistencesocialnetwork.dto.RebelDTO;
import org.george.swresistencesocialnetwork.repository.RebelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Rebel Service Test")
public class RebelServiceTest {

    @Autowired
    public RebelRepository rebelRepository;

    public RebelService rebelServiceImp;

    @BeforeEach
    void init() {
        rebelServiceImp = new RebelService(rebelRepository);
    }

    @Test
    @DisplayName("rebel service is not null")
    void test() {
        Assertions.assertNotNull(rebelServiceImp);
    }
}
