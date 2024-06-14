package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnfoldingMapperTest {

    private UnfoldingMapper unfoldingMapper;

    @BeforeEach
    public void setUp() {
        unfoldingMapper = new UnfoldingMapperImpl();
    }
}
