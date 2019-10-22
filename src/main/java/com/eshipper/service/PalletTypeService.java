package com.eshipper.service;

import com.eshipper.service.dto.PalletTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PalletTypeDTO> findAll(Pageable pageable);


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
