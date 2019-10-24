package com.eshipper.service;

import com.eshipper.service.dto.PalletTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.PalletType}.
 */
public interface PalletTypeService {

    /**
     * Save a palletType.
     *
     * @param palletTypeDTO the entity to save.
     * @return the persisted entity.
     */
    PalletTypeDTO save(PalletTypeDTO palletTypeDTO);

    /**
     * Get all the palletTypes.
     *
     * @return the list of entities.
     */
    List<PalletTypeDTO> findAll();


    /**
     * Get the "id" palletType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PalletTypeDTO> findOne(Long id);

    /**
     * Delete the "id" palletType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
