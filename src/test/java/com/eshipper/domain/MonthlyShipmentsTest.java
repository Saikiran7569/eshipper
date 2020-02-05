package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class MonthlyShipmentsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MonthlyShipments.class);
        MonthlyShipments monthlyShipments1 = new MonthlyShipments();
        monthlyShipments1.setId(1L);
        MonthlyShipments monthlyShipments2 = new MonthlyShipments();
        monthlyShipments2.setId(monthlyShipments1.getId());
        assertThat(monthlyShipments1).isEqualTo(monthlyShipments2);
        monthlyShipments2.setId(2L);
        assertThat(monthlyShipments1).isNotEqualTo(monthlyShipments2);
        monthlyShipments1.setId(null);
        assertThat(monthlyShipments1).isNotEqualTo(monthlyShipments2);
    }
}
