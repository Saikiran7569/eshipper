package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class MonthlyShipmentsMapperTest {

    private MonthlyShipmentsMapper monthlyShipmentsMapper;

    @BeforeEach
    public void setUp() {
        monthlyShipmentsMapper = new MonthlyShipmentsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(monthlyShipmentsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(monthlyShipmentsMapper.fromId(null)).isNull();
    }
}
