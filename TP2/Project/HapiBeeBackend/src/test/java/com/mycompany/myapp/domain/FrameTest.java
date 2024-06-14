package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FrameTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Frame.class);
        Frame frame1 = new Frame();
        frame1.setId(1L);
        Frame frame2 = new Frame();
        frame2.setId(frame1.getId());
        assertThat(frame1).isEqualTo(frame2);
        frame2.setId(2L);
        assertThat(frame1).isNotEqualTo(frame2);
        frame1.setId(null);
        assertThat(frame1).isNotEqualTo(frame2);
    }
}
