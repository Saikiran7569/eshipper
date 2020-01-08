package com.eshipper.service;

import com.eshipper.service.dto.SuppliesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.Supplies}.
 */
public interface SuppliesService {

    /**
     * Save a supplies.
     *
     * @param suppliesDTO the entity to save.
     * @return the persisted entity.
     */
    SuppliesDTO save(SuppliesDTO suppliesDTO);

    /**
     * Get all the supplies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SuppliesDTO> findAll(Pageable pageable);


    /**
     * Get the "id" supplies.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SuppliesDTO> findOne(Long id);

    /**
     * Delete the "id" supplies.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
