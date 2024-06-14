package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CrestMapperTest {

    private CrestMapper crestMapper;

    @BeforeEach
    public void setUp() {
        crestMapper = new CrestMapperImpl();
    }
}
