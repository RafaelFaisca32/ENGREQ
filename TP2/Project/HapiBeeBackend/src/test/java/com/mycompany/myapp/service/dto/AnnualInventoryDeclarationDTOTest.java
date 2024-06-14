package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnnualInventoryDeclarationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnnualInventoryDeclarationDTO.class);
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO1 = new AnnualInventoryDeclarationDTO();
        annualInventoryDeclarationDTO1.setId(1L);
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO2 = new AnnualInventoryDeclarationDTO();
        assertThat(annualInventoryDeclarationDTO1).isNotEqualTo(annualInventoryDeclarationDTO2);
        annualInventoryDeclarationDTO2.setId(annualInventoryDeclarationDTO1.getId());
        assertThat(annualInventoryDeclarationDTO1).isEqualTo(annualInventoryDeclarationDTO2);
        annualInventoryDeclarationDTO2.setId(2L);
        assertThat(annualInventoryDeclarationDTO1).isNotEqualTo(annualInventoryDeclarationDTO2);
        annualInventoryDeclarationDTO1.setId(null);
        assertThat(annualInventoryDeclarationDTO1).isNotEqualTo(annualInventoryDeclarationDTO2);
    }
}
