package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TranshumanceRequestMapperTest {

    private TranshumanceRequestMapper transhumanceRequestMapper;

    @BeforeEach
    public void setUp() {
        transhumanceRequestMapper = new TranshumanceRequestMapperImpl();
    }
}
