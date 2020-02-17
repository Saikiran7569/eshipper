package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.SupplyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Supply} and its DTO {@link SupplyDTO}.
 */
@Mapper(componentModel = "spring", uses = {CarrierMapper.class})
public interface SupplyMapper extends EntityMapper<SupplyDTO, Supply> {

    @Mapping(source = "carrier.id", target = "carrierId")
    SupplyDTO toDto(Supply supply);

    @Mapping(source = "carrierId", target = "carrier")
    Supply toEntity(SupplyDTO supplyDTO);

    default Supply fromId(Long id) {
        if (id == null) {
            return null;
        }
        Supply supply = new Supply();
        supply.setId(id);
        return supply;
    }
}
