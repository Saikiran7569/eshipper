package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SuppliesOrdersMapperTest {

    private SuppliesOrdersMapper suppliesOrdersMapper;

    @BeforeEach
    public void setUp() {
        suppliesOrdersMapper = new SuppliesOrdersMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(suppliesOrdersMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(suppliesOrdersMapper.fromId(null)).isNull();
    }
}
