package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UnfoldingDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UnfoldingDTO.class);
        UnfoldingDTO unfoldingDTO1 = new UnfoldingDTO();
        unfoldingDTO1.setId(1L);
        UnfoldingDTO unfoldingDTO2 = new UnfoldingDTO();
        assertThat(unfoldingDTO1).isNotEqualTo(unfoldingDTO2);
        unfoldingDTO2.setId(unfoldingDTO1.getId());
        assertThat(unfoldingDTO1).isEqualTo(unfoldingDTO2);
        unfoldingDTO2.setId(2L);
        assertThat(unfoldingDTO1).isNotEqualTo(unfoldingDTO2);
        unfoldingDTO1.setId(null);
        assertThat(unfoldingDTO1).isNotEqualTo(unfoldingDTO2);
    }
}
