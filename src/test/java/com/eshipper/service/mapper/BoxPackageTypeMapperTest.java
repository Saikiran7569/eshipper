package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class BoxPackageTypeMapperTest {

    private BoxPackageTypeMapper boxPackageTypeMapper;

    @BeforeEach
    public void setUp() {
        boxPackageTypeMapper = new BoxPackageTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(boxPackageTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(boxPackageTypeMapper.fromId(null)).isNull();
    }
}
