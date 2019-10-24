package com.eshipper.service.impl;

import com.eshipper.service.ShipmentPackageService;
import com.eshipper.domain.ShipmentPackage;
import com.eshipper.repository.ShipmentPackageRepository;
import com.eshipper.service.dto.ShipmentPackageDTO;
import com.eshipper.service.mapper.ShipmentPackageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ShipmentPackage}.
 */
@Service
@Transactional
public class ShipmentPackageServiceImpl implements ShipmentPackageService {

    private final Logger log = LoggerFactory.getLogger(ShipmentPackageServiceImpl.class);

    private final ShipmentPackageRepository shipmentPackageRepository;

    private final ShipmentPackageMapper shipmentPackageMapper;

    public ShipmentPackageServiceImpl(ShipmentPackageRepository shipmentPackageRepository, ShipmentPackageMapper shipmentPackageMapper) {
        this.shipmentPackageRepository = shipmentPackageRepository;
        this.shipmentPackageMapper = shipmentPackageMapper;
    }

    /**
     * Save a shipmentPackage.
     *
     * @param shipmentPackageDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ShipmentPackageDTO save(ShipmentPackageDTO shipmentPackageDTO) {
        log.debug("Request to save ShipmentPackage : {}", shipmentPackageDTO);
        ShipmentPackage shipmentPackage = shipmentPackageMapper.toEntity(shipmentPackageDTO);
        shipmentPackage = shipmentPackageRepository.save(shipmentPackage);
        return shipmentPackageMapper.toDto(shipmentPackage);
    }

    /**
     * Get all the shipmentPackages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ShipmentPackageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ShipmentPackages");
        return shipmentPackageRepository.findAll(pageable)
            .map(shipmentPackageMapper::toDto);
    }


    /**
     * Get one shipmentPackage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ShipmentPackageDTO> findOne(Long id) {
        log.debug("Request to get ShipmentPackage : {}", id);
        return shipmentPackageRepository.findById(id)
            .map(shipmentPackageMapper::toDto);
    }

    /**
     * Delete the shipmentPackage by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ShipmentPackage : {}", id);
        shipmentPackageRepository.deleteById(id);
    }
}
