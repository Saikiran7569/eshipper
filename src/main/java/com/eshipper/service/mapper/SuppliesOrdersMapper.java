package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.SuppliesOrdersDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SuppliesOrders} and its DTO {@link SuppliesOrdersDTO}.
 */
@Mapper(componentModel = "spring", uses = {User11Mapper.class})
public interface SuppliesOrdersMapper extends EntityMapper<SuppliesOrdersDTO, SuppliesOrders> {

    @Mapping(source = "createdByUser.id", target = "createdByUserId")
    SuppliesOrdersDTO toDto(SuppliesOrders suppliesOrders);

    @Mapping(source = "createdByUserId", target = "createdByUser")
    SuppliesOrders toEntity(SuppliesOrdersDTO suppliesOrdersDTO);

    default SuppliesOrders fromId(Long id) {
        if (id == null) {
            return null;
        }
        SuppliesOrders suppliesOrders = new SuppliesOrders();
        suppliesOrders.setId(id);
        return suppliesOrders;
    }
}
