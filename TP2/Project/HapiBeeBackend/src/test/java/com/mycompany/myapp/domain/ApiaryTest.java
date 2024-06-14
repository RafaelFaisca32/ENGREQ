package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ApiaryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Apiary.class);
        Apiary apiary1 = new Apiary();
        apiary1.setId(1L);
        Apiary apiary2 = new Apiary();
        apiary2.setId(apiary1.getId());
        assertThat(apiary1).isEqualTo(apiary2);
        apiary2.setId(2L);
        assertThat(apiary1).isNotEqualTo(apiary2);
        apiary1.setId(null);
        assertThat(apiary1).isNotEqualTo(apiary2);
    }
}
