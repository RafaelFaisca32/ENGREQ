package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnnualInventoryDeclarationMapperTest {

    private AnnualInventoryDeclarationMapper annualInventoryDeclarationMapper;

    @BeforeEach
    public void setUp() {
        annualInventoryDeclarationMapper = new AnnualInventoryDeclarationMapperImpl();
    }
}
