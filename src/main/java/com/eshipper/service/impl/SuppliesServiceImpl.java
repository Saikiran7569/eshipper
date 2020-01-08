package com.eshipper.service.impl;

import com.eshipper.service.SuppliesService;
import com.eshipper.domain.Supplies;
import com.eshipper.repository.SuppliesRepository;
import com.eshipper.service.dto.SuppliesDTO;
import com.eshipper.service.mapper.SuppliesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Supplies}.
 */
@Service
@Transactional
public class SuppliesServiceImpl implements SuppliesService {

    private final Logger log = LoggerFactory.getLogger(SuppliesServiceImpl.class);

    private final SuppliesRepository suppliesRepository;

    private final SuppliesMapper suppliesMapper;

    public SuppliesServiceImpl(SuppliesRepository suppliesRepository, SuppliesMapper suppliesMapper) {
        this.suppliesRepository = suppliesRepository;
        this.suppliesMapper = suppliesMapper;
    }

    /**
     * Save a supplies.
     *
     * @param suppliesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SuppliesDTO save(SuppliesDTO suppliesDTO) {
        log.debug("Request to save Supplies : {}", suppliesDTO);
        Supplies supplies = suppliesMapper.toEntity(suppliesDTO);
        supplies = suppliesRepository.save(supplies);
        return suppliesMapper.toDto(supplies);
    }

    /**
     * Get all the supplies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SuppliesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Supplies");
        return suppliesRepository.findAll(pageable)
            .map(suppliesMapper::toDto);
    }


    /**
     * Get one supplies by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SuppliesDTO> findOne(Long id) {
        log.debug("Request to get Supplies : {}", id);
        return suppliesRepository.findById(id)
            .map(suppliesMapper::toDto);
    }

    /**
     * Delete the supplies by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Supplies : {}", id);
        suppliesRepository.deleteById(id);
    }
}
