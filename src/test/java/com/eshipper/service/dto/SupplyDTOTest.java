package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class SupplyDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupplyDTO.class);
        SupplyDTO supplyDTO1 = new SupplyDTO();
        supplyDTO1.setId(1L);
        SupplyDTO supplyDTO2 = new SupplyDTO();
        assertThat(supplyDTO1).isNotEqualTo(supplyDTO2);
        supplyDTO2.setId(supplyDTO1.getId());
        assertThat(supplyDTO1).isEqualTo(supplyDTO2);
        supplyDTO2.setId(2L);
        assertThat(supplyDTO1).isNotEqualTo(supplyDTO2);
        supplyDTO1.setId(null);
        assertThat(supplyDTO1).isNotEqualTo(supplyDTO2);
    }
}
