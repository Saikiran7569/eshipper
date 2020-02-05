package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CustomsTaxBillToDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomsTaxBillToDTO.class);
        CustomsTaxBillToDTO customsTaxBillToDTO1 = new CustomsTaxBillToDTO();
        customsTaxBillToDTO1.setId(1L);
        CustomsTaxBillToDTO customsTaxBillToDTO2 = new CustomsTaxBillToDTO();
        assertThat(customsTaxBillToDTO1).isNotEqualTo(customsTaxBillToDTO2);
        customsTaxBillToDTO2.setId(customsTaxBillToDTO1.getId());
        assertThat(customsTaxBillToDTO1).isEqualTo(customsTaxBillToDTO2);
        customsTaxBillToDTO2.setId(2L);
        assertThat(customsTaxBillToDTO1).isNotEqualTo(customsTaxBillToDTO2);
        customsTaxBillToDTO1.setId(null);
        assertThat(customsTaxBillToDTO1).isNotEqualTo(customsTaxBillToDTO2);
    }
}
