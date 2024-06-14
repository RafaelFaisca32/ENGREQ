package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnnualInventoryDeclarationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnnualInventoryDeclaration.class);
        AnnualInventoryDeclaration annualInventoryDeclaration1 = new AnnualInventoryDeclaration();
        annualInventoryDeclaration1.setId(1L);
        AnnualInventoryDeclaration annualInventoryDeclaration2 = new AnnualInventoryDeclaration();
        annualInventoryDeclaration2.setId(annualInventoryDeclaration1.getId());
        assertThat(annualInventoryDeclaration1).isEqualTo(annualInventoryDeclaration2);
        annualInventoryDeclaration2.setId(2L);
        assertThat(annualInventoryDeclaration1).isNotEqualTo(annualInventoryDeclaration2);
        annualInventoryDeclaration1.setId(null);
        assertThat(annualInventoryDeclaration1).isNotEqualTo(annualInventoryDeclaration2);
    }
}
