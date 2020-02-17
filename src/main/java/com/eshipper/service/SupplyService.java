package com.eshipper.service;

import com.eshipper.service.dto.SupplyDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.Supply}.
 */
public interface SupplyService {

    /**
     * Save a supply.
     *
     * @param supplyDTO the entity to save.
     * @return the persisted entity.
     */
    SupplyDTO save(SupplyDTO supplyDTO);

    /**
     * Get all the supplies.
     *
     * @return the list of entities.
     */
    List<SupplyDTO> findAll();

    /**
     * Get the "id" supply.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SupplyDTO> findOne(Long id);

    /**
     * Delete the "id" supply.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
