package com.eshipper.service;

import com.eshipper.service.dto.ShipmentPackageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ShipmentPackage}.
 */
public interface ShipmentPackageService {

    /**
     * Save a shipmentPackage.
     *
     * @param shipmentPackageDTO the entity to save.
     * @return the persisted entity.
     */
    ShipmentPackageDTO save(ShipmentPackageDTO shipmentPackageDTO);

    /**
     * Get all the shipmentPackages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ShipmentPackageDTO> findAll(Pageable pageable);


    /**
     * Get the "id" shipmentPackage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ShipmentPackageDTO> findOne(Long id);

    /**
     * Delete the "id" shipmentPackage.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
