package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BeekeeperMapperTest {

    private BeekeeperMapper beekeeperMapper;

    @BeforeEach
    public void setUp() {
        beekeeperMapper = new BeekeeperMapperImpl();
    }
}
