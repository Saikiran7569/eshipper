package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class MonthlyShipmentsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MonthlyShipmentsDTO.class);
        MonthlyShipmentsDTO monthlyShipmentsDTO1 = new MonthlyShipmentsDTO();
        monthlyShipmentsDTO1.setId(1L);
        MonthlyShipmentsDTO monthlyShipmentsDTO2 = new MonthlyShipmentsDTO();
        assertThat(monthlyShipmentsDTO1).isNotEqualTo(monthlyShipmentsDTO2);
        monthlyShipmentsDTO2.setId(monthlyShipmentsDTO1.getId());
        assertThat(monthlyShipmentsDTO1).isEqualTo(monthlyShipmentsDTO2);
        monthlyShipmentsDTO2.setId(2L);
        assertThat(monthlyShipmentsDTO1).isNotEqualTo(monthlyShipmentsDTO2);
        monthlyShipmentsDTO1.setId(null);
        assertThat(monthlyShipmentsDTO1).isNotEqualTo(monthlyShipmentsDTO2);
    }
}
