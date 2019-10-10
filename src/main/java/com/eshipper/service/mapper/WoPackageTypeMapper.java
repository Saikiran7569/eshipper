package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.WoPackageTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoPackageType} and its DTO {@link WoPackageTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WoPackageTypeMapper extends EntityMapper<WoPackageTypeDTO, WoPackageType> {


    @Mapping(target = "boxes", ignore = true)
    @Mapping(target = "removeBox", ignore = true)
    WoPackageType toEntity(WoPackageTypeDTO woPackageTypeDTO);

    default WoPackageType fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoPackageType woPackageType = new WoPackageType();
        woPackageType.setId(id);
        return woPackageType;
    }
}
