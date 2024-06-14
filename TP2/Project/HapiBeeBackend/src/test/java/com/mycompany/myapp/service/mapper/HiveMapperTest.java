package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HiveMapperTest {

    private HiveMapper hiveMapper;

    @BeforeEach
    public void setUp() {
        hiveMapper = new HiveMapperImpl();
    }
}
