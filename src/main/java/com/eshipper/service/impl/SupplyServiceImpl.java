package com.eshipper.service.impl;

import com.eshipper.service.SupplyService;
import com.eshipper.domain.Supply;
import com.eshipper.repository.SupplyRepository;
import com.eshipper.service.dto.SupplyDTO;
import com.eshipper.service.mapper.SupplyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Supply}.
 */
@Service
@Transactional
public class SupplyServiceImpl implements SupplyService {

    private final Logger log = LoggerFactory.getLogger(SupplyServiceImpl.class);

    private final SupplyRepository supplyRepository;

    private final SupplyMapper supplyMapper;

    public SupplyServiceImpl(SupplyRepository supplyRepository, SupplyMapper supplyMapper) {
        this.supplyRepository = supplyRepository;
        this.supplyMapper = supplyMapper;
    }

    /**
     * Save a supply.
     *
     * @param supplyDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SupplyDTO save(SupplyDTO supplyDTO) {
        log.debug("Request to save Supply : {}", supplyDTO);
        Supply supply = supplyMapper.toEntity(supplyDTO);
        supply = supplyRepository.save(supply);
        return supplyMapper.toDto(supply);
    }

    /**
     * Get all the supplies.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SupplyDTO> findAll() {
        log.debug("Request to get all Supplies");
        return supplyRepository.findAll().stream()
            .map(supplyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one supply by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SupplyDTO> findOne(Long id) {
        log.debug("Request to get Supply : {}", id);
        return supplyRepository.findById(id)
            .map(supplyMapper::toDto);
    }

    /**
     * Delete the supply by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Supply : {}", id);
        supplyRepository.deleteById(id);
    }
}
