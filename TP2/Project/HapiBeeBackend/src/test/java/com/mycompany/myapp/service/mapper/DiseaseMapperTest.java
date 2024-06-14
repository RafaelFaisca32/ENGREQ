package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiseaseMapperTest {

    private DiseaseMapper diseaseMapper;

    @BeforeEach
    public void setUp() {
        diseaseMapper = new DiseaseMapperImpl();
    }
}
