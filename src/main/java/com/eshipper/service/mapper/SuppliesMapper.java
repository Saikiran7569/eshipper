package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.SuppliesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Supplies} and its DTO {@link SuppliesDTO}.
 */
@Mapper(componentModel = "spring", uses = {CarrierMapper.class})
public interface SuppliesMapper extends EntityMapper<SuppliesDTO, Supplies> {

    @Mapping(source = "supplies.id", target = "suppliesId")
    SuppliesDTO toDto(Supplies supplies);

    @Mapping(source = "suppliesId", target = "supplies")
    Supplies toEntity(SuppliesDTO suppliesDTO);

    default Supplies fromId(Long id) {
        if (id == null) {
            return null;
        }
        Supplies supplies = new Supplies();
        supplies.setId(id);
        return supplies;
    }
}
