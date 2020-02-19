package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class User11DTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(User11DTO.class);
        User11DTO user11DTO1 = new User11DTO();
        user11DTO1.setId(1L);
        User11DTO user11DTO2 = new User11DTO();
        assertThat(user11DTO1).isNotEqualTo(user11DTO2);
        user11DTO2.setId(user11DTO1.getId());
        assertThat(user11DTO1).isEqualTo(user11DTO2);
        user11DTO2.setId(2L);
        assertThat(user11DTO1).isNotEqualTo(user11DTO2);
        user11DTO1.setId(null);
        assertThat(user11DTO1).isNotEqualTo(user11DTO2);
    }
}
