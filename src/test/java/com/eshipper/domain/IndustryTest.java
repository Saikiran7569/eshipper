package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class IndustryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Industry.class);
        Industry industry1 = new Industry();
        industry1.setId(1L);
        Industry industry2 = new Industry();
        industry2.setId(industry1.getId());
        assertThat(industry1).isEqualTo(industry2);
        industry2.setId(2L);
        assertThat(industry1).isNotEqualTo(industry2);
        industry1.setId(null);
        assertThat(industry1).isNotEqualTo(industry2);
    }
}
