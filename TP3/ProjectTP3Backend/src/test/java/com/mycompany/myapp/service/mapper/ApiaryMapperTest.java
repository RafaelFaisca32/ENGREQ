package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApiaryMapperTest {

    private ApiaryMapper apiaryMapper;

    @BeforeEach
    public void setUp() {
        apiaryMapper = new ApiaryMapperImpl();
    }
}
