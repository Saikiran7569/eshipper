package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CommodityTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommodityType.class);
        CommodityType commodityType1 = new CommodityType();
        commodityType1.setId(1L);
        CommodityType commodityType2 = new CommodityType();
        commodityType2.setId(commodityType1.getId());
        assertThat(commodityType1).isEqualTo(commodityType2);
        commodityType2.setId(2L);
        assertThat(commodityType1).isNotEqualTo(commodityType2);
        commodityType1.setId(null);
        assertThat(commodityType1).isNotEqualTo(commodityType2);
    }
}
