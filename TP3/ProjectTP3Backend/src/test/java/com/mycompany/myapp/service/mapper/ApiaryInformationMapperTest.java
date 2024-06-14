package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApiaryInformationMapperTest {

    private ApiaryInformationMapper apiaryInformationMapper;

    @BeforeEach
    public void setUp() {
        apiaryInformationMapper = new ApiaryInformationMapperImpl();
    }
}
