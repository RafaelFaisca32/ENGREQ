package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TranshumanceRequestDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TranshumanceRequestDTO.class);
        TranshumanceRequestDTO transhumanceRequestDTO1 = new TranshumanceRequestDTO();
        transhumanceRequestDTO1.setId(1L);
        TranshumanceRequestDTO transhumanceRequestDTO2 = new TranshumanceRequestDTO();
        assertThat(transhumanceRequestDTO1).isNotEqualTo(transhumanceRequestDTO2);
        transhumanceRequestDTO2.setId(transhumanceRequestDTO1.getId());
        assertThat(transhumanceRequestDTO1).isEqualTo(transhumanceRequestDTO2);
        transhumanceRequestDTO2.setId(2L);
        assertThat(transhumanceRequestDTO1).isNotEqualTo(transhumanceRequestDTO2);
        transhumanceRequestDTO1.setId(null);
        assertThat(transhumanceRequestDTO1).isNotEqualTo(transhumanceRequestDTO2);
    }
}
