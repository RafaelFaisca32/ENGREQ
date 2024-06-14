package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TranshumanceRequestTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TranshumanceRequest.class);
        TranshumanceRequest transhumanceRequest1 = new TranshumanceRequest();
        transhumanceRequest1.setId(1L);
        TranshumanceRequest transhumanceRequest2 = new TranshumanceRequest();
        transhumanceRequest2.setId(transhumanceRequest1.getId());
        assertThat(transhumanceRequest1).isEqualTo(transhumanceRequest2);
        transhumanceRequest2.setId(2L);
        assertThat(transhumanceRequest1).isNotEqualTo(transhumanceRequest2);
        transhumanceRequest1.setId(null);
        assertThat(transhumanceRequest1).isNotEqualTo(transhumanceRequest2);
    }
}
