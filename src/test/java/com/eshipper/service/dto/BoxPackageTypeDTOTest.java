package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class BoxPackageTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BoxPackageTypeDTO.class);
        BoxPackageTypeDTO boxPackageTypeDTO1 = new BoxPackageTypeDTO();
        boxPackageTypeDTO1.setId(1L);
        BoxPackageTypeDTO boxPackageTypeDTO2 = new BoxPackageTypeDTO();
        assertThat(boxPackageTypeDTO1).isNotEqualTo(boxPackageTypeDTO2);
        boxPackageTypeDTO2.setId(boxPackageTypeDTO1.getId());
        assertThat(boxPackageTypeDTO1).isEqualTo(boxPackageTypeDTO2);
        boxPackageTypeDTO2.setId(2L);
        assertThat(boxPackageTypeDTO1).isNotEqualTo(boxPackageTypeDTO2);
        boxPackageTypeDTO1.setId(null);
        assertThat(boxPackageTypeDTO1).isNotEqualTo(boxPackageTypeDTO2);
    }
}
