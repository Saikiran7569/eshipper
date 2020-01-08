package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class SuppliesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Supplies.class);
        Supplies supplies1 = new Supplies();
        supplies1.setId(1L);
        Supplies supplies2 = new Supplies();
        supplies2.setId(supplies1.getId());
        assertThat(supplies1).isEqualTo(supplies2);
        supplies2.setId(2L);
        assertThat(supplies1).isNotEqualTo(supplies2);
        supplies1.setId(null);
        assertThat(supplies1).isNotEqualTo(supplies2);
    }
}
