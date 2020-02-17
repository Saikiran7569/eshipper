package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.CarrierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Carrier} and its DTO {@link CarrierDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CarrierMapper extends EntityMapper<CarrierDTO, Carrier> {


    @Mapping(target = "supplies", ignore = true)
    @Mapping(target = "removeSupply", ignore = true)
    Carrier toEntity(CarrierDTO carrierDTO);

    default Carrier fromId(Long id) {
        if (id == null) {
            return null;
        }
        Carrier carrier = new Carrier();
        carrier.setId(id);
        return carrier;
    }
}
