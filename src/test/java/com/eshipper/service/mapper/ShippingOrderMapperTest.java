package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ShippingOrderMapperTest {

    private ShippingOrderMapper shippingOrderMapper;

    @BeforeEach
    public void setUp() {
        shippingOrderMapper = new ShippingOrderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(shippingOrderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(shippingOrderMapper.fromId(null)).isNull();
    }
}
