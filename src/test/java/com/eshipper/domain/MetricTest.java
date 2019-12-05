package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class MetricTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Metric.class);
        Metric metric1 = new Metric();
        metric1.setId(1L);
        Metric metric2 = new Metric();
        metric2.setId(metric1.getId());
        assertThat(metric1).isEqualTo(metric2);
        metric2.setId(2L);
        assertThat(metric1).isNotEqualTo(metric2);
        metric1.setId(null);
        assertThat(metric1).isNotEqualTo(metric2);
    }
}
