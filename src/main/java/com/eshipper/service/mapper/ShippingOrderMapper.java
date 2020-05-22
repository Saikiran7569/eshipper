package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.ShippingOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShippingOrder} and its DTO {@link ShippingOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {CommodityTypeMapper.class})
public interface ShippingOrderMapper extends EntityMapper<ShippingOrderDTO, ShippingOrder> {

    @Mapping(source = "commodityType.id", target = "commodityTypeId")
    ShippingOrderDTO toDto(ShippingOrder shippingOrder);

    @Mapping(source = "commodityTypeId", target = "commodityType")
    ShippingOrder toEntity(ShippingOrderDTO shippingOrderDTO);

    default ShippingOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShippingOrder shippingOrder = new ShippingOrder();
        shippingOrder.setId(id);
        return shippingOrder;
    }
}
