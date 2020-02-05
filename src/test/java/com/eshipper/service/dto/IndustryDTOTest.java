package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class IndustryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IndustryDTO.class);
        IndustryDTO industryDTO1 = new IndustryDTO();
        industryDTO1.setId(1L);
        IndustryDTO industryDTO2 = new IndustryDTO();
        assertThat(industryDTO1).isNotEqualTo(industryDTO2);
        industryDTO2.setId(industryDTO1.getId());
        assertThat(industryDTO1).isEqualTo(industryDTO2);
        industryDTO2.setId(2L);
        assertThat(industryDTO1).isNotEqualTo(industryDTO2);
        industryDTO1.setId(null);
        assertThat(industryDTO1).isNotEqualTo(industryDTO2);
    }
}
