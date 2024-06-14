package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BeekeeperDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BeekeeperDTO.class);
        BeekeeperDTO beekeeperDTO1 = new BeekeeperDTO();
        beekeeperDTO1.setId(1L);
        BeekeeperDTO beekeeperDTO2 = new BeekeeperDTO();
        assertThat(beekeeperDTO1).isNotEqualTo(beekeeperDTO2);
        beekeeperDTO2.setId(beekeeperDTO1.getId());
        assertThat(beekeeperDTO1).isEqualTo(beekeeperDTO2);
        beekeeperDTO2.setId(2L);
        assertThat(beekeeperDTO1).isNotEqualTo(beekeeperDTO2);
        beekeeperDTO1.setId(null);
        assertThat(beekeeperDTO1).isNotEqualTo(beekeeperDTO2);
    }
}
