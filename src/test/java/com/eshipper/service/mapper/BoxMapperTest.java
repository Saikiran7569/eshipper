package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class BoxMapperTest {

    private BoxMapper boxMapper;

    @BeforeEach
    public void setUp() {
        boxMapper = new BoxMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(boxMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(boxMapper.fromId(null)).isNull();
    }
}
