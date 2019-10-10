package com.eshipper.service;

import com.eshipper.service.dto.WoPackageTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.WoPackageType}.
 */
public interface WoPackageTypeService {

    /**
     * Save a woPackageType.
     *
     * @param woPackageTypeDTO the entity to save.
     * @return the persisted entity.
     */
    WoPackageTypeDTO save(WoPackageTypeDTO woPackageTypeDTO);

    /**
     * Get all the woPackageTypes.
     *
     * @return the list of entities.
     */
    List<WoPackageTypeDTO> findAll();


    /**
     * Get the "id" woPackageType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoPackageTypeDTO> findOne(Long id);

    /**
     * Delete the "id" woPackageType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
