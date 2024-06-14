package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ApiaryZoneTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApiaryZone.class);
        ApiaryZone apiaryZone1 = new ApiaryZone();
        apiaryZone1.setId(1L);
        ApiaryZone apiaryZone2 = new ApiaryZone();
        apiaryZone2.setId(apiaryZone1.getId());
        assertThat(apiaryZone1).isEqualTo(apiaryZone2);
        apiaryZone2.setId(2L);
        assertThat(apiaryZone1).isNotEqualTo(apiaryZone2);
        apiaryZone1.setId(null);
        assertThat(apiaryZone1).isNotEqualTo(apiaryZone2);
    }
}
