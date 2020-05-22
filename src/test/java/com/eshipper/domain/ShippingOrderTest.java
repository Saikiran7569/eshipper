package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ShippingOrderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShippingOrder.class);
        ShippingOrder shippingOrder1 = new ShippingOrder();
        shippingOrder1.setId(1L);
        ShippingOrder shippingOrder2 = new ShippingOrder();
        shippingOrder2.setId(shippingOrder1.getId());
        assertThat(shippingOrder1).isEqualTo(shippingOrder2);
        shippingOrder2.setId(2L);
        assertThat(shippingOrder1).isNotEqualTo(shippingOrder2);
        shippingOrder1.setId(null);
        assertThat(shippingOrder1).isNotEqualTo(shippingOrder2);
    }
}
