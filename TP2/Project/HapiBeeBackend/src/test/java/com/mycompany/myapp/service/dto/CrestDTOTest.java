package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CrestDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CrestDTO.class);
        CrestDTO crestDTO1 = new CrestDTO();
        crestDTO1.setId(1L);
        CrestDTO crestDTO2 = new CrestDTO();
        assertThat(crestDTO1).isNotEqualTo(crestDTO2);
        crestDTO2.setId(crestDTO1.getId());
        assertThat(crestDTO1).isEqualTo(crestDTO2);
        crestDTO2.setId(2L);
        assertThat(crestDTO1).isNotEqualTo(crestDTO2);
        crestDTO1.setId(null);
        assertThat(crestDTO1).isNotEqualTo(crestDTO2);
    }
}
