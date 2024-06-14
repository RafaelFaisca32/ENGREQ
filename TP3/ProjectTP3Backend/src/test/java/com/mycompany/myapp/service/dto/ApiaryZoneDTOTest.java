package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ApiaryZoneDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApiaryZoneDTO.class);
        ApiaryZoneDTO apiaryZoneDTO1 = new ApiaryZoneDTO();
        apiaryZoneDTO1.setId(1L);
        ApiaryZoneDTO apiaryZoneDTO2 = new ApiaryZoneDTO();
        assertThat(apiaryZoneDTO1).isNotEqualTo(apiaryZoneDTO2);
        apiaryZoneDTO2.setId(apiaryZoneDTO1.getId());
        assertThat(apiaryZoneDTO1).isEqualTo(apiaryZoneDTO2);
        apiaryZoneDTO2.setId(2L);
        assertThat(apiaryZoneDTO1).isNotEqualTo(apiaryZoneDTO2);
        apiaryZoneDTO1.setId(null);
        assertThat(apiaryZoneDTO1).isNotEqualTo(apiaryZoneDTO2);
    }
}
