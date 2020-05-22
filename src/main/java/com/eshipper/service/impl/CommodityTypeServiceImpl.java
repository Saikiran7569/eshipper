package com.eshipper.service.impl;

import com.eshipper.service.CommodityTypeService;
import com.eshipper.domain.CommodityType;
import com.eshipper.repository.CommodityTypeRepository;
import com.eshipper.service.dto.CommodityTypeDTO;
import com.eshipper.service.mapper.CommodityTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CommodityType}.
 */
@Service
@Transactional
public class CommodityTypeServiceImpl implements CommodityTypeService {

    private final Logger log = LoggerFactory.getLogger(CommodityTypeServiceImpl.class);

    private final CommodityTypeRepository commodityTypeRepository;

    private final CommodityTypeMapper commodityTypeMapper;

    public CommodityTypeServiceImpl(CommodityTypeRepository commodityTypeRepository, CommodityTypeMapper commodityTypeMapper) {
        this.commodityTypeRepository = commodityTypeRepository;
        this.commodityTypeMapper = commodityTypeMapper;
    }

    /**
     * Save a commodityType.
     *
     * @param commodityTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CommodityTypeDTO save(CommodityTypeDTO commodityTypeDTO) {
        log.debug("Request to save CommodityType : {}", commodityTypeDTO);
        CommodityType commodityType = commodityTypeMapper.toEntity(commodityTypeDTO);
        commodityType = commodityTypeRepository.save(commodityType);
        return commodityTypeMapper.toDto(commodityType);
    }

    /**
     * Get all the commodityTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommodityTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommodityTypes");
        return commodityTypeRepository.findAll(pageable)
            .map(commodityTypeMapper::toDto);
    }


    /**
     * Get one commodityType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CommodityTypeDTO> findOne(Long id) {
        log.debug("Request to get CommodityType : {}", id);
        return commodityTypeRepository.findById(id)
            .map(commodityTypeMapper::toDto);
    }

    /**
     * Delete the commodityType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CommodityType : {}", id);

        commodityTypeRepository.deleteById(id);
    }
}
