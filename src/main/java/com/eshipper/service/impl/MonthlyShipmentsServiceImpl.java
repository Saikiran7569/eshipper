package com.eshipper.service.impl;

import com.eshipper.service.MonthlyShipmentsService;
import com.eshipper.domain.MonthlyShipments;
import com.eshipper.repository.MonthlyShipmentsRepository;
import com.eshipper.service.dto.MonthlyShipmentsDTO;
import com.eshipper.service.mapper.MonthlyShipmentsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MonthlyShipments}.
 */
@Service
@Transactional
public class MonthlyShipmentsServiceImpl implements MonthlyShipmentsService {

    private final Logger log = LoggerFactory.getLogger(MonthlyShipmentsServiceImpl.class);

    private final MonthlyShipmentsRepository monthlyShipmentsRepository;

    private final MonthlyShipmentsMapper monthlyShipmentsMapper;

    public MonthlyShipmentsServiceImpl(MonthlyShipmentsRepository monthlyShipmentsRepository, MonthlyShipmentsMapper monthlyShipmentsMapper) {
        this.monthlyShipmentsRepository = monthlyShipmentsRepository;
        this.monthlyShipmentsMapper = monthlyShipmentsMapper;
    }

    /**
     * Save a monthlyShipments.
     *
     * @param monthlyShipmentsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MonthlyShipmentsDTO save(MonthlyShipmentsDTO monthlyShipmentsDTO) {
        log.debug("Request to save MonthlyShipments : {}", monthlyShipmentsDTO);
        MonthlyShipments monthlyShipments = monthlyShipmentsMapper.toEntity(monthlyShipmentsDTO);
        monthlyShipments = monthlyShipmentsRepository.save(monthlyShipments);
        return monthlyShipmentsMapper.toDto(monthlyShipments);
    }

    /**
     * Get all the monthlyShipments.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<MonthlyShipmentsDTO> findAll() {
        log.debug("Request to get all MonthlyShipments");
        return monthlyShipmentsRepository.findAll().stream()
            .map(monthlyShipmentsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one monthlyShipments by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MonthlyShipmentsDTO> findOne(Long id) {
        log.debug("Request to get MonthlyShipments : {}", id);
        return monthlyShipmentsRepository.findById(id)
            .map(monthlyShipmentsMapper::toDto);
    }

    /**
     * Delete the monthlyShipments by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MonthlyShipments : {}", id);
        monthlyShipmentsRepository.deleteById(id);
    }
}
