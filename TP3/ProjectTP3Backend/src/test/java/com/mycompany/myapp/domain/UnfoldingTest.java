package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UnfoldingTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Unfolding.class);
        Unfolding unfolding1 = new Unfolding();
        unfolding1.setId(1L);
        Unfolding unfolding2 = new Unfolding();
        unfolding2.setId(unfolding1.getId());
        assertThat(unfolding1).isEqualTo(unfolding2);
        unfolding2.setId(2L);
        assertThat(unfolding1).isNotEqualTo(unfolding2);
        unfolding1.setId(null);
        assertThat(unfolding1).isNotEqualTo(unfolding2);
    }
}
