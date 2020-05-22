package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CommodityTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommodityTypeDTO.class);
        CommodityTypeDTO commodityTypeDTO1 = new CommodityTypeDTO();
        commodityTypeDTO1.setId(1L);
        CommodityTypeDTO commodityTypeDTO2 = new CommodityTypeDTO();
        assertThat(commodityTypeDTO1).isNotEqualTo(commodityTypeDTO2);
        commodityTypeDTO2.setId(commodityTypeDTO1.getId());
        assertThat(commodityTypeDTO1).isEqualTo(commodityTypeDTO2);
        commodityTypeDTO2.setId(2L);
        assertThat(commodityTypeDTO1).isNotEqualTo(commodityTypeDTO2);
        commodityTypeDTO1.setId(null);
        assertThat(commodityTypeDTO1).isNotEqualTo(commodityTypeDTO2);
    }
}
