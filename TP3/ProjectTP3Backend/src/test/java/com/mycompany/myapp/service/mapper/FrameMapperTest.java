package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrameMapperTest {

    private FrameMapper frameMapper;

    @BeforeEach
    public void setUp() {
        frameMapper = new FrameMapperImpl();
    }
}
