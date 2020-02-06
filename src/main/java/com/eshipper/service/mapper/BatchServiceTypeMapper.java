package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.BatchServiceTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BatchServiceType} and its DTO {@link BatchServiceTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BatchServiceTypeMapper extends EntityMapper<BatchServiceTypeDTO, BatchServiceType> {



    default BatchServiceType fromId(Long id) {
        if (id == null) {
            return null;
        }
        BatchServiceType batchServiceType = new BatchServiceType();
        batchServiceType.setId(id);
        return batchServiceType;
    }
}
