package com.eshipper.service;

import com.eshipper.service.dto.BoxPackageTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.BoxPackageType}.
 */
public interface BoxPackageTypeService {

    /**
     * Save a boxPackageType.
     *
     * @param boxPackageTypeDTO the entity to save.
     * @return the persisted entity.
     */
    BoxPackageTypeDTO save(BoxPackageTypeDTO boxPackageTypeDTO);

    /**
     * Get all the boxPackageTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BoxPackageTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" boxPackageType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BoxPackageTypeDTO> findOne(Long id);

    /**
     * Delete the "id" boxPackageType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
