package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.AddressBookDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AddressBook} and its DTO {@link AddressBookDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class, ProvinceMapper.class, CityMapper.class, CompanyMapper.class})
public interface AddressBookMapper extends EntityMapper<AddressBookDTO, AddressBook> {

    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "company.id", target = "companyId")
    AddressBookDTO toDto(AddressBook addressBook);

    @Mapping(source = "countryId", target = "country")
    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "cityId", target = "city")
    @Mapping(source = "companyId", target = "company")
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
