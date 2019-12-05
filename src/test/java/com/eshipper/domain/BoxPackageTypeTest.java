package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class BoxPackageTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BoxPackageType.class);
        BoxPackageType boxPackageType1 = new BoxPackageType();
        boxPackageType1.setId(1L);
        BoxPackageType boxPackageType2 = new BoxPackageType();
        boxPackageType2.setId(boxPackageType1.getId());
        assertThat(boxPackageType1).isEqualTo(boxPackageType2);
        boxPackageType2.setId(2L);
        assertThat(boxPackageType1).isNotEqualTo(boxPackageType2);
        boxPackageType1.setId(null);
        assertThat(boxPackageType1).isNotEqualTo(boxPackageType2);
    }
}
