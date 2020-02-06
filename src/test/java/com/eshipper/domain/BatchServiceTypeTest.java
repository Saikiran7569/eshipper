package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class BatchServiceTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BatchServiceType.class);
        BatchServiceType batchServiceType1 = new BatchServiceType();
        batchServiceType1.setId(1L);
        BatchServiceType batchServiceType2 = new BatchServiceType();
        batchServiceType2.setId(batchServiceType1.getId());
        assertThat(batchServiceType1).isEqualTo(batchServiceType2);
        batchServiceType2.setId(2L);
        assertThat(batchServiceType1).isNotEqualTo(batchServiceType2);
        batchServiceType1.setId(null);
        assertThat(batchServiceType1).isNotEqualTo(batchServiceType2);
    }
}
