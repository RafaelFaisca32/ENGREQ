package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ApiaryInformationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApiaryInformationDTO.class);
        ApiaryInformationDTO apiaryInformationDTO1 = new ApiaryInformationDTO();
        apiaryInformationDTO1.setId(1L);
        ApiaryInformationDTO apiaryInformationDTO2 = new ApiaryInformationDTO();
        assertThat(apiaryInformationDTO1).isNotEqualTo(apiaryInformationDTO2);
        apiaryInformationDTO2.setId(apiaryInformationDTO1.getId());
        assertThat(apiaryInformationDTO1).isEqualTo(apiaryInformationDTO2);
        apiaryInformationDTO2.setId(2L);
        assertThat(apiaryInformationDTO1).isNotEqualTo(apiaryInformationDTO2);
        apiaryInformationDTO1.setId(null);
        assertThat(apiaryInformationDTO1).isNotEqualTo(apiaryInformationDTO2);
    }
}
