package com.eshipper.service.impl;

import com.eshipper.service.BoxPackageTypeService;
import com.eshipper.domain.BoxPackageType;
import com.eshipper.repository.BoxPackageTypeRepository;
import com.eshipper.service.dto.BoxPackageTypeDTO;
import com.eshipper.service.mapper.BoxPackageTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BoxPackageType}.
 */
@Service
@Transactional
public class BoxPackageTypeServiceImpl implements BoxPackageTypeService {

    private final Logger log = LoggerFactory.getLogger(BoxPackageTypeServiceImpl.class);

    private final BoxPackageTypeRepository boxPackageTypeRepository;

    private final BoxPackageTypeMapper boxPackageTypeMapper;

    public BoxPackageTypeServiceImpl(BoxPackageTypeRepository boxPackageTypeRepository, BoxPackageTypeMapper boxPackageTypeMapper) {
        this.boxPackageTypeRepository = boxPackageTypeRepository;
        this.boxPackageTypeMapper = boxPackageTypeMapper;
    }

    /**
     * Save a boxPackageType.
     *
     * @param boxPackageTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BoxPackageTypeDTO save(BoxPackageTypeDTO boxPackageTypeDTO) {
        log.debug("Request to save BoxPackageType : {}", boxPackageTypeDTO);
        BoxPackageType boxPackageType = boxPackageTypeMapper.toEntity(boxPackageTypeDTO);
        boxPackageType = boxPackageTypeRepository.save(boxPackageType);
        return boxPackageTypeMapper.toDto(boxPackageType);
    }

    /**
     * Get all the boxPackageTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BoxPackageTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BoxPackageTypes");
        return boxPackageTypeRepository.findAll(pageable)
            .map(boxPackageTypeMapper::toDto);
    }


    /**
     * Get one boxPackageType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BoxPackageTypeDTO> findOne(Long id) {
        log.debug("Request to get BoxPackageType : {}", id);
        return boxPackageTypeRepository.findById(id)
            .map(boxPackageTypeMapper::toDto);
    }

    /**
     * Delete the boxPackageType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BoxPackageType : {}", id);
        boxPackageTypeRepository.deleteById(id);
    }
}
