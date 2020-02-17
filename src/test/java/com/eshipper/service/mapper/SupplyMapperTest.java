package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SupplyMapperTest {

    private SupplyMapper supplyMapper;

    @BeforeEach
    public void setUp() {
        supplyMapper = new SupplyMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(supplyMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(supplyMapper.fromId(null)).isNull();
    }
}
