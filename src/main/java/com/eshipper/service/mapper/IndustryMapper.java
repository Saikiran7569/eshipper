package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.IndustryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Industry} and its DTO {@link IndustryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface IndustryMapper extends EntityMapper<IndustryDTO, Industry> {



    default Industry fromId(Long id) {
        if (id == null) {
            return null;
        }
        Industry industry = new Industry();
        industry.setId(id);
        return industry;
    }
}
