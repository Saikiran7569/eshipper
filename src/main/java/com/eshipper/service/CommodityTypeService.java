package com.eshipper.service;

import com.eshipper.service.dto.CommodityTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.CommodityType}.
 */
public interface CommodityTypeService {

    /**
     * Save a commodityType.
     *
     * @param commodityTypeDTO the entity to save.
     * @return the persisted entity.
     */
    CommodityTypeDTO save(CommodityTypeDTO commodityTypeDTO);

    /**
     * Get all the commodityTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CommodityTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" commodityType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommodityTypeDTO> findOne(Long id);

    /**
     * Delete the "id" commodityType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
