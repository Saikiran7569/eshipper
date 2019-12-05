package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.BoxPackageTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BoxPackageType} and its DTO {@link BoxPackageTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BoxPackageTypeMapper extends EntityMapper<BoxPackageTypeDTO, BoxPackageType> {



    default BoxPackageType fromId(Long id) {
        if (id == null) {
            return null;
        }
        BoxPackageType boxPackageType = new BoxPackageType();
        boxPackageType.setId(id);
        return boxPackageType;
    }
}
