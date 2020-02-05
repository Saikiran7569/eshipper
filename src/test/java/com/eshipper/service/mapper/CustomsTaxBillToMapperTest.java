package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CustomsTaxBillToMapperTest {

    private CustomsTaxBillToMapper customsTaxBillToMapper;

    @BeforeEach
    public void setUp() {
        customsTaxBillToMapper = new CustomsTaxBillToMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(customsTaxBillToMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(customsTaxBillToMapper.fromId(null)).isNull();
    }
}
