package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SuppliesMapperTest {

    private SuppliesMapper suppliesMapper;

    @BeforeEach
    public void setUp() {
        suppliesMapper = new SuppliesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(suppliesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(suppliesMapper.fromId(null)).isNull();
    }
}
