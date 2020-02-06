package com.eshipper.service.impl;

import com.eshipper.service.BatchServiceTypeService;
import com.eshipper.domain.BatchServiceType;
import com.eshipper.repository.BatchServiceTypeRepository;
import com.eshipper.service.dto.BatchServiceTypeDTO;
import com.eshipper.service.mapper.BatchServiceTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link BatchServiceType}.
 */
@Service
@Transactional
public class BatchServiceTypeServiceImpl implements BatchServiceTypeService {

    private final Logger log = LoggerFactory.getLogger(BatchServiceTypeServiceImpl.class);

    private final BatchServiceTypeRepository batchServiceTypeRepository;

    private final BatchServiceTypeMapper batchServiceTypeMapper;

    public BatchServiceTypeServiceImpl(BatchServiceTypeRepository batchServiceTypeRepository, BatchServiceTypeMapper batchServiceTypeMapper) {
        this.batchServiceTypeRepository = batchServiceTypeRepository;
        this.batchServiceTypeMapper = batchServiceTypeMapper;
    }

    /**
     * Save a batchServiceType.
     *
     * @param batchServiceTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BatchServiceTypeDTO save(BatchServiceTypeDTO batchServiceTypeDTO) {
        log.debug("Request to save BatchServiceType : {}", batchServiceTypeDTO);
        BatchServiceType batchServiceType = batchServiceTypeMapper.toEntity(batchServiceTypeDTO);
        batchServiceType = batchServiceTypeRepository.save(batchServiceType);
        return batchServiceTypeMapper.toDto(batchServiceType);
    }

    /**
     * Get all the batchServiceTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<BatchServiceTypeDTO> findAll() {
        log.debug("Request to get all BatchServiceTypes");
        return batchServiceTypeRepository.findAll().stream()
            .map(batchServiceTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one batchServiceType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BatchServiceTypeDTO> findOne(Long id) {
        log.debug("Request to get BatchServiceType : {}", id);
        return batchServiceTypeRepository.findById(id)
            .map(batchServiceTypeMapper::toDto);
    }

    /**
     * Delete the batchServiceType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BatchServiceType : {}", id);
        batchServiceTypeRepository.deleteById(id);
    }
}
