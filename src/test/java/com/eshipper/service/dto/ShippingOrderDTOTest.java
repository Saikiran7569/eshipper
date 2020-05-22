package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ShippingOrderDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShippingOrderDTO.class);
        ShippingOrderDTO shippingOrderDTO1 = new ShippingOrderDTO();
        shippingOrderDTO1.setId(1L);
        ShippingOrderDTO shippingOrderDTO2 = new ShippingOrderDTO();
        assertThat(shippingOrderDTO1).isNotEqualTo(shippingOrderDTO2);
        shippingOrderDTO2.setId(shippingOrderDTO1.getId());
        assertThat(shippingOrderDTO1).isEqualTo(shippingOrderDTO2);
        shippingOrderDTO2.setId(2L);
        assertThat(shippingOrderDTO1).isNotEqualTo(shippingOrderDTO2);
        shippingOrderDTO1.setId(null);
        assertThat(shippingOrderDTO1).isNotEqualTo(shippingOrderDTO2);
    }
}
