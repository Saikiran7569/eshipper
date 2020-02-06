package com.eshipper.service;

import com.eshipper.service.dto.BatchServiceTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.BatchServiceType}.
 */
public interface BatchServiceTypeService {

    /**
     * Save a batchServiceType.
     *
     * @param batchServiceTypeDTO the entity to save.
     * @return the persisted entity.
     */
    BatchServiceTypeDTO save(BatchServiceTypeDTO batchServiceTypeDTO);

    /**
     * Get all the batchServiceTypes.
     *
     * @return the list of entities.
     */
    List<BatchServiceTypeDTO> findAll();


    /**
     * Get the "id" batchServiceType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BatchServiceTypeDTO> findOne(Long id);

    /**
     * Delete the "id" batchServiceType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
