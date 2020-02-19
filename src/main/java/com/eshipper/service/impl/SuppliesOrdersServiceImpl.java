package com.eshipper.service.impl;

import com.eshipper.service.SuppliesOrdersService;
import com.eshipper.domain.SuppliesOrders;
import com.eshipper.repository.SuppliesOrdersRepository;
import com.eshipper.service.dto.SuppliesOrdersDTO;
import com.eshipper.service.mapper.SuppliesOrdersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SuppliesOrders}.
 */
@Service
@Transactional
public class SuppliesOrdersServiceImpl implements SuppliesOrdersService {

    private final Logger log = LoggerFactory.getLogger(SuppliesOrdersServiceImpl.class);

    private final SuppliesOrdersRepository suppliesOrdersRepository;

    private final SuppliesOrdersMapper suppliesOrdersMapper;

    public SuppliesOrdersServiceImpl(SuppliesOrdersRepository suppliesOrdersRepository, SuppliesOrdersMapper suppliesOrdersMapper) {
        this.suppliesOrdersRepository = suppliesOrdersRepository;
        this.suppliesOrdersMapper = suppliesOrdersMapper;
    }

    /**
     * Save a suppliesOrders.
     *
     * @param suppliesOrdersDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SuppliesOrdersDTO save(SuppliesOrdersDTO suppliesOrdersDTO) {
        log.debug("Request to save SuppliesOrders : {}", suppliesOrdersDTO);
        SuppliesOrders suppliesOrders = suppliesOrdersMapper.toEntity(suppliesOrdersDTO);
        suppliesOrders = suppliesOrdersRepository.save(suppliesOrders);
        return suppliesOrdersMapper.toDto(suppliesOrders);
    }

    /**
     * Get all the suppliesOrders.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SuppliesOrdersDTO> findAll() {
        log.debug("Request to get all SuppliesOrders");
        return suppliesOrdersRepository.findAll().stream()
            .map(suppliesOrdersMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one suppliesOrders by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SuppliesOrdersDTO> findOne(Long id) {
        log.debug("Request to get SuppliesOrders : {}", id);
        return suppliesOrdersRepository.findById(id)
            .map(suppliesOrdersMapper::toDto);
    }

    /**
     * Delete the suppliesOrders by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SuppliesOrders : {}", id);
        suppliesOrdersRepository.deleteById(id);
    }
}
