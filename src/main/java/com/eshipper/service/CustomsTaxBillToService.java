package com.eshipper.service;

import com.eshipper.service.dto.CustomsTaxBillToDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.CustomsTaxBillTo}.
 */
public interface CustomsTaxBillToService {

    /**
     * Save a customsTaxBillTo.
     *
     * @param customsTaxBillToDTO the entity to save.
     * @return the persisted entity.
     */
    CustomsTaxBillToDTO save(CustomsTaxBillToDTO customsTaxBillToDTO);

    /**
     * Get all the customsTaxBillTos.
     *
     * @return the list of entities.
     */
    List<CustomsTaxBillToDTO> findAll();


    /**
     * Get the "id" customsTaxBillTo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomsTaxBillToDTO> findOne(Long id);

    /**
     * Delete the "id" customsTaxBillTo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
