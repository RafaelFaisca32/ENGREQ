package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApiaryZoneMapperTest {

    private ApiaryZoneMapper apiaryZoneMapper;

    @BeforeEach
    public void setUp() {
        apiaryZoneMapper = new ApiaryZoneMapperImpl();
    }
}