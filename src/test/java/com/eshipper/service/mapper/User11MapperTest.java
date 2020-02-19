package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class User11MapperTest {

    private User11Mapper user11Mapper;

    @BeforeEach
    public void setUp() {
        user11Mapper = new User11MapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(user11Mapper.fromId(id).getId()).isEqualTo(id);
        assertThat(user11Mapper.fromId(null)).isNull();
    }
}
