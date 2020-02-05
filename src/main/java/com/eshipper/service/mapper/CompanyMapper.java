package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.CompanyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Company} and its DTO {@link CompanyDTO}.
 */
@Mapper(componentModel = "spring", uses = {IndustryMapper.class, MonthlyShipmentsMapper.class})
public interface CompanyMapper extends EntityMapper<CompanyDTO, Company> {

    @Mapping(source = "industry.id", target = "industryId")
    @Mapping(source = "monthlyshipments.id", target = "monthlyshipmentsId")
    CompanyDTO toDto(Company company);

    @Mapping(source = "industryId", target = "industry")
    @Mapping(source = "monthlyshipmentsId", target = "monthlyshipments")
    Company toEntity(CompanyDTO companyDTO);

    default Company fromId(Long id) {
        if (id == null) {
            return null;
        }
        Company company = new Company();
        company.setId(id);
        return company;
    }
}
