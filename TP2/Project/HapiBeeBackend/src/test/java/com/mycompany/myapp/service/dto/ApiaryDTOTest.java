package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ApiaryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApiaryDTO.class);
        ApiaryDTO apiaryDTO1 = new ApiaryDTO();
        apiaryDTO1.setId(1L);
        ApiaryDTO apiaryDTO2 = new ApiaryDTO();
        assertThat(apiaryDTO1).isNotEqualTo(apiaryDTO2);
        apiaryDTO2.setId(apiaryDTO1.getId());
        assertThat(apiaryDTO1).isEqualTo(apiaryDTO2);
        apiaryDTO2.setId(2L);
        assertThat(apiaryDTO1).isNotEqualTo(apiaryDTO2);
        apiaryDTO1.setId(null);
        assertThat(apiaryDTO1).isNotEqualTo(apiaryDTO2);
    }
}
