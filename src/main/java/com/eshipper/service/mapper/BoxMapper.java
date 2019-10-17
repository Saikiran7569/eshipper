package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.BoxDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Box} and its DTO {@link BoxDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, MetricMapper.class, WoPackageTypeMapper.class})
public interface BoxMapper extends EntityMapper<BoxDTO, Box> {

    @Mapping(source = "createdByUser.id", target = "createdByUserId")
    @Mapping(source = "metric.id", target = "metricId")
    @Mapping(source = "woPackageType.id", target = "woPackageTypeId")
    BoxDTO toDto(Box box);

    @Mapping(source = "createdByUserId", target = "createdByUser")
    @Mapping(source = "metricId", target = "metric")
    @Mapping(source = "woPackageTypeId", target = "woPackageType")
    Box toEntity(BoxDTO boxDTO);

    default Box fromId(Long id) {
        if (id == null) {
            return null;
        }
        Box box = new Box();
        box.setId(id);
        return box;
    }
}
