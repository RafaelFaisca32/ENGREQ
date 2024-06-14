package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HiveDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HiveDTO.class);
        HiveDTO hiveDTO1 = new HiveDTO();
        hiveDTO1.setId(1L);
        HiveDTO hiveDTO2 = new HiveDTO();
        assertThat(hiveDTO1).isNotEqualTo(hiveDTO2);
        hiveDTO2.setId(hiveDTO1.getId());
        assertThat(hiveDTO1).isEqualTo(hiveDTO2);
        hiveDTO2.setId(2L);
        assertThat(hiveDTO1).isNotEqualTo(hiveDTO2);
        hiveDTO1.setId(null);
        assertThat(hiveDTO1).isNotEqualTo(hiveDTO2);
    }
}
