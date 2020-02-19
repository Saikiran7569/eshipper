package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.User11DTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link User11} and its DTO {@link User11DTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface User11Mapper extends EntityMapper<User11DTO, User11> {


    @Mapping(target = "suppliesOrders", ignore = true)
    @Mapping(target = "removeSuppliesOrders", ignore = true)
    User11 toEntity(User11DTO user11DTO);

    default User11 fromId(Long id) {
        if (id == null) {
            return null;
        }
        User11 user11 = new User11();
        user11.setId(id);
        return user11;
    }
}
