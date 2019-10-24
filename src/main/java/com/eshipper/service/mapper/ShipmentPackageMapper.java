package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ShipmentPackageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShipmentPackage} and its DTO {@link ShipmentPackageDTO}.
 */
@Mapper(componentModel = "spring", uses = {PalletTypeMapper.class})
public interface ShipmentPackageMapper extends EntityMapper<ShipmentPackageDTO, ShipmentPackage> {

    @Mapping(source = "type.id", target = "typeId")
    ShipmentPackageDTO toDto(ShipmentPackage shipmentPackage);

    @Mapping(source = "typeId", target = "type")
    ShipmentPackage toEntity(ShipmentPackageDTO shipmentPackageDTO);

    default ShipmentPackage fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShipmentPackage shipmentPackage = new ShipmentPackage();
        shipmentPackage.setId(id);
        return shipmentPackage;
    }
}
