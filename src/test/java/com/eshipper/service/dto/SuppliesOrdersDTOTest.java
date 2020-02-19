package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class SuppliesOrdersDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SuppliesOrdersDTO.class);
        SuppliesOrdersDTO suppliesOrdersDTO1 = new SuppliesOrdersDTO();
        suppliesOrdersDTO1.setId(1L);
        SuppliesOrdersDTO suppliesOrdersDTO2 = new SuppliesOrdersDTO();
        assertThat(suppliesOrdersDTO1).isNotEqualTo(suppliesOrdersDTO2);
        suppliesOrdersDTO2.setId(suppliesOrdersDTO1.getId());
        assertThat(suppliesOrdersDTO1).isEqualTo(suppliesOrdersDTO2);
        suppliesOrdersDTO2.setId(2L);
        assertThat(suppliesOrdersDTO1).isNotEqualTo(suppliesOrdersDTO2);
        suppliesOrdersDTO1.setId(null);
        assertThat(suppliesOrdersDTO1).isNotEqualTo(suppliesOrdersDTO2);
    }
}
