package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class BatchServiceTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BatchServiceTypeDTO.class);
        BatchServiceTypeDTO batchServiceTypeDTO1 = new BatchServiceTypeDTO();
        batchServiceTypeDTO1.setId(1L);
        BatchServiceTypeDTO batchServiceTypeDTO2 = new BatchServiceTypeDTO();
        assertThat(batchServiceTypeDTO1).isNotEqualTo(batchServiceTypeDTO2);
        batchServiceTypeDTO2.setId(batchServiceTypeDTO1.getId());
        assertThat(batchServiceTypeDTO1).isEqualTo(batchServiceTypeDTO2);
        batchServiceTypeDTO2.setId(2L);
        assertThat(batchServiceTypeDTO1).isNotEqualTo(batchServiceTypeDTO2);
        batchServiceTypeDTO1.setId(null);
        assertThat(batchServiceTypeDTO1).isNotEqualTo(batchServiceTypeDTO2);
    }
}
