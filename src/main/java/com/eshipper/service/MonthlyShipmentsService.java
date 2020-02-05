package com.eshipper.service;

import com.eshipper.service.dto.MonthlyShipmentsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.MonthlyShipments}.
 */
public interface MonthlyShipmentsService {

    /**
     * Save a monthlyShipments.
     *
     * @param monthlyShipmentsDTO the entity to save.
     * @return the persisted entity.
     */
    MonthlyShipmentsDTO save(MonthlyShipmentsDTO monthlyShipmentsDTO);

    /**
     * Get all the monthlyShipments.
     *
     * @return the list of entities.
     */
    List<MonthlyShipmentsDTO> findAll();


    /**
     * Get the "id" monthlyShipments.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MonthlyShipmentsDTO> findOne(Long id);

    /**
     * Delete the "id" monthlyShipments.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
