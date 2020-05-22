package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.CommodityTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommodityType} and its DTO {@link CommodityTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommodityTypeMapper extends EntityMapper<CommodityTypeDTO, CommodityType> {



    default CommodityType fromId(Long id) {
        if (id == null) {
            return null;
        }
        CommodityType commodityType = new CommodityType();
        commodityType.setId(id);
        return commodityType;
    }
}
