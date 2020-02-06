package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class BatchServiceTypeMapperTest {

    private BatchServiceTypeMapper batchServiceTypeMapper;

    @BeforeEach
    public void setUp() {
        batchServiceTypeMapper = new BatchServiceTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(batchServiceTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(batchServiceTypeMapper.fromId(null)).isNull();
    }
}
