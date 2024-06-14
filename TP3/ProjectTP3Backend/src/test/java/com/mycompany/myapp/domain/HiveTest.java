package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HiveTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hive.class);
        Hive hive1 = new Hive();
        hive1.setId(1L);
        Hive hive2 = new Hive();
        hive2.setId(hive1.getId());
        assertThat(hive1).isEqualTo(hive2);
        hive2.setId(2L);
        assertThat(hive1).isNotEqualTo(hive2);
        hive1.setId(null);
        assertThat(hive1).isNotEqualTo(hive2);
    }
}
