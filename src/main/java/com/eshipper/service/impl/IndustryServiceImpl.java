package com.eshipper.service.impl;

import com.eshipper.service.IndustryService;
import com.eshipper.domain.Industry;
import com.eshipper.repository.IndustryRepository;
import com.eshipper.service.dto.IndustryDTO;
import com.eshipper.service.mapper.IndustryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Industry}.
 */
@Service
@Transactional
public class IndustryServiceImpl implements IndustryService {

    private final Logger log = LoggerFactory.getLogger(IndustryServiceImpl.class);

    private final IndustryRepository industryRepository;

    private final IndustryMapper industryMapper;

    public IndustryServiceImpl(IndustryRepository industryRepository, IndustryMapper industryMapper) {
        this.industryRepository = industryRepository;
        this.industryMapper = industryMapper;
    }

    /**
     * Save a industry.
     *
     * @param industryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public IndustryDTO save(IndustryDTO industryDTO) {
        log.debug("Request to save Industry : {}", industryDTO);
        Industry industry = industryMapper.toEntity(industryDTO);
        industry = industryRepository.save(industry);
        return industryMapper.toDto(industry);
    }

    /**
     * Get all the industries.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<IndustryDTO> findAll() {
        log.debug("Request to get all Industries");
        return industryRepository.findAll().stream()
            .map(industryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one industry by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IndustryDTO> findOne(Long id) {
        log.debug("Request to get Industry : {}", id);
        return industryRepository.findById(id)
            .map(industryMapper::toDto);
    }

    /**
     * Delete the industry by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Industry : {}", id);
        industryRepository.deleteById(id);
    }
}
