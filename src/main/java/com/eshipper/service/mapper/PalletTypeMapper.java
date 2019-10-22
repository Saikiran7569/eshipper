package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.PalletTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PalletType} and its DTO {@link PalletTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PalletTypeMapper extends EntityMapper<PalletTypeDTO, PalletType> {


    @Mapping(target = "boxes", ignore = true)
    @Mapping(target = "removeBox", ignore = true)
    PalletType toEntity(PalletTypeDTO palletTypeDTO);

    default PalletType fromId(Long id) {
        if (id == null) {
            return null;
        }
        PalletType palletType = new PalletType();
        palletType.setId(id);
        return palletType;
    }
}
