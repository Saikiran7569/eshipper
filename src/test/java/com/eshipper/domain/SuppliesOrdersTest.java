package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class SuppliesOrdersTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SuppliesOrders.class);
        SuppliesOrders suppliesOrders1 = new SuppliesOrders();
        suppliesOrders1.setId(1L);
        SuppliesOrders suppliesOrders2 = new SuppliesOrders();
        suppliesOrders2.setId(suppliesOrders1.getId());
        assertThat(suppliesOrders1).isEqualTo(suppliesOrders2);
        suppliesOrders2.setId(2L);
        assertThat(suppliesOrders1).isNotEqualTo(suppliesOrders2);
        suppliesOrders1.setId(null);
        assertThat(suppliesOrders1).isNotEqualTo(suppliesOrders2);
    }
}
