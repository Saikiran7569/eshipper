package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CustomsTaxBillToTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomsTaxBillTo.class);
        CustomsTaxBillTo customsTaxBillTo1 = new CustomsTaxBillTo();
        customsTaxBillTo1.setId(1L);
        CustomsTaxBillTo customsTaxBillTo2 = new CustomsTaxBillTo();
        customsTaxBillTo2.setId(customsTaxBillTo1.getId());
        assertThat(customsTaxBillTo1).isEqualTo(customsTaxBillTo2);
        customsTaxBillTo2.setId(2L);
        assertThat(customsTaxBillTo1).isNotEqualTo(customsTaxBillTo2);
        customsTaxBillTo1.setId(null);
        assertThat(customsTaxBillTo1).isNotEqualTo(customsTaxBillTo2);
    }
}
