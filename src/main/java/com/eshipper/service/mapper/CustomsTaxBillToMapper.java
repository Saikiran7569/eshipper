package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.CustomsTaxBillToDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CustomsTaxBillTo} and its DTO {@link CustomsTaxBillToDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomsTaxBillToMapper extends EntityMapper<CustomsTaxBillToDTO, CustomsTaxBillTo> {



    default CustomsTaxBillTo fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomsTaxBillTo customsTaxBillTo = new CustomsTaxBillTo();
        customsTaxBillTo.setId(id);
        return customsTaxBillTo;
    }
}
