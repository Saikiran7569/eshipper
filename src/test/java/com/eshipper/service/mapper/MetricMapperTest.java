package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class MetricMapperTest {

    private MetricMapper metricMapper;

    @BeforeEach
    public void setUp() {
        metricMapper = new MetricMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(metricMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(metricMapper.fromId(null)).isNull();
    }
}
