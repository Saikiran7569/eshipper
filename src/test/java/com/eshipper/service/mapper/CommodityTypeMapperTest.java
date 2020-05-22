package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CommodityTypeMapperTest {

    private CommodityTypeMapper commodityTypeMapper;

    @BeforeEach
    public void setUp() {
        commodityTypeMapper = new CommodityTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(commodityTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(commodityTypeMapper.fromId(null)).isNull();
    }
}
