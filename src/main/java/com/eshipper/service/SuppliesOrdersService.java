package com.eshipper.service;

import com.eshipper.service.dto.SuppliesOrdersDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.SuppliesOrders}.
 */
public interface SuppliesOrdersService {

    /**
     * Save a suppliesOrders.
     *
     * @param suppliesOrdersDTO the entity to save.
     * @return the persisted entity.
     */
    SuppliesOrdersDTO save(SuppliesOrdersDTO suppliesOrdersDTO);

    /**
     * Get all the suppliesOrders.
     *
     * @return the list of entities.
     */
    List<SuppliesOrdersDTO> findAll();

    /**
     * Get the "id" suppliesOrders.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SuppliesOrdersDTO> findOne(Long id);

    /**
     * Delete the "id" suppliesOrders.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
