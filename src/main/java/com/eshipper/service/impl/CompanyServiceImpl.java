package com.eshipper.service.impl;

import com.eshipper.service.CompanyService;
import com.eshipper.domain.Company;
import com.eshipper.repository.CompanyRepository;
import com.eshipper.service.dto.CompanyDTO;
import com.eshipper.service.mapper.CompanyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Company}.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    /**
     * Save a company.
     *
     * @param companyDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        log.debug("Request to save Company : {}", companyDTO);
        Company company = companyMapper.toEntity(companyDTO);
        company = companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    /**
     * Get all the companies.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CompanyDTO> findAll() {
        log.debug("Request to get all Companies");
        return companyRepository.findAll().stream()
            .map(companyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one company by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CompanyDTO> findOne(Long id) {
        log.debug("Request to get Company : {}", id);
        return companyRepository.findById(id)
            .map(companyMapper::toDto);
    }

    /**
     * Delete the company by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Company : {}", id);
        companyRepository.deleteById(id);
    }
}
