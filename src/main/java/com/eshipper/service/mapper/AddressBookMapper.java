package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.AddressBookDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AddressBook} and its DTO {@link AddressBookDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class, ProvinceMapper.class, CityMapper.class, CompanyMapper.class, User1Mapper.class})
public interface AddressBookMapper extends EntityMapper<AddressBookDTO, AddressBook> {

    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "user1.id", target = "user1Id")
    AddressBookDTO toDto(AddressBook addressBook);

    @Mapping(source = "countryId", target = "country")
    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "cityId", target = "city")
    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "user1Id", target = "user1")
    AddressBook toEntity(AddressBookDTO addressBookDTO);

    default AddressBook fromId(Long id) {
        if (id == null) {
            return null;
        }
        AddressBook addressBook = new AddressBook();
        addressBook.setId(id);
        return addressBook;
    }
}
