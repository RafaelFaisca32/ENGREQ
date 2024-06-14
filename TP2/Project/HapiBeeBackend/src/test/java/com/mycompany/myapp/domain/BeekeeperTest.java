package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BeekeeperTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Beekeeper.class);
        Beekeeper beekeeper1 = new Beekeeper();
        beekeeper1.setId(1L);
        Beekeeper beekeeper2 = new Beekeeper();
        beekeeper2.setId(beekeeper1.getId());
        assertThat(beekeeper1).isEqualTo(beekeeper2);
        beekeeper2.setId(2L);
        assertThat(beekeeper1).isNotEqualTo(beekeeper2);
        beekeeper1.setId(null);
        assertThat(beekeeper1).isNotEqualTo(beekeeper2);
    }
}
