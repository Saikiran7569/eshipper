package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.MonthlyShipmentsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MonthlyShipments} and its DTO {@link MonthlyShipmentsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MonthlyShipmentsMapper extends EntityMapper<MonthlyShipmentsDTO, MonthlyShipments> {



    default MonthlyShipments fromId(Long id) {
        if (id == null) {
            return null;
        }
        MonthlyShipments monthlyShipments = new MonthlyShipments();
        monthlyShipments.setId(id);
        return monthlyShipments;
    }
}
