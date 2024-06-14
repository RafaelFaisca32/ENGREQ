package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ApiaryInformationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApiaryInformation.class);
        ApiaryInformation apiaryInformation1 = new ApiaryInformation();
        apiaryInformation1.setId(1L);
        ApiaryInformation apiaryInformation2 = new ApiaryInformation();
        apiaryInformation2.setId(apiaryInformation1.getId());
        assertThat(apiaryInformation1).isEqualTo(apiaryInformation2);
        apiaryInformation2.setId(2L);
        assertThat(apiaryInformation1).isNotEqualTo(apiaryInformation2);
        apiaryInformation1.setId(null);
        assertThat(apiaryInformation1).isNotEqualTo(apiaryInformation2);
    }
}
