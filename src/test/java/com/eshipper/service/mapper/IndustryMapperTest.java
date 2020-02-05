package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class IndustryMapperTest {

    private IndustryMapper industryMapper;

    @BeforeEach
    public void setUp() {
        industryMapper = new IndustryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(industryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(industryMapper.fromId(null)).isNull();
    }
}
