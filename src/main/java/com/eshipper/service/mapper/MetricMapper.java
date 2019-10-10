package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.MetricDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Metric} and its DTO {@link MetricDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MetricMapper extends EntityMapper<MetricDTO, Metric> {


    @Mapping(target = "boxes", ignore = true)
    @Mapping(target = "removeBox", ignore = true)
    Metric toEntity(MetricDTO metricDTO);

    default Metric fromId(Long id) {
        if (id == null) {
            return null;
        }
        Metric metric = new Metric();
        metric.setId(id);
        return metric;
    }
}
