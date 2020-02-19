package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class User11Test {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(User11.class);
        User11 user111 = new User11();
        user111.setId(1L);
        User11 user112 = new User11();
        user112.setId(user111.getId());
        assertThat(user111).isEqualTo(user112);
        user112.setId(2L);
        assertThat(user111).isNotEqualTo(user112);
        user111.setId(null);
        assertThat(user111).isNotEqualTo(user112);
    }
}
