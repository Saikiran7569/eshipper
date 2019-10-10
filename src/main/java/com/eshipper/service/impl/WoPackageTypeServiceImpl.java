package com.eshipper.service.impl;

import com.eshipper.service.WoPackageTypeService;
import com.eshipper.domain.WoPackageType;
import com.eshipper.repository.WoPackageTypeRepository;
import com.eshipper.service.dto.WoPackageTypeDTO;
import com.eshipper.service.mapper.WoPackageTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WoPackageType}.
 */
@Service
@Transactional
public class WoPackageTypeServiceImpl implements WoPackageTypeService {

    private final Logger log = LoggerFactory.getLogger(WoPackageTypeServiceImpl.class);

    private final WoPackageTypeRepository woPackageTypeRepository;

    private final WoPackageTypeMapper woPackageTypeMapper;

    public WoPackageTypeServiceImpl(WoPackageTypeRepository woPackageTypeRepository, WoPackageTypeMapper woPackageTypeMapper) {
        this.woPackageTypeRepository = woPackageTypeRepository;
        this.woPackageTypeMapper = woPackageTypeMapper;
    }

    /**
     * Save a woPackageType.
     *
     * @param woPackageTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoPackageTypeDTO save(WoPackageTypeDTO woPackageTypeDTO) {
        log.debug("Request to save WoPackageType : {}", woPackageTypeDTO);
        WoPackageType woPackageType = woPackageTypeMapper.toEntity(woPackageTypeDTO);
        woPackageType = woPackageTypeRepository.save(woPackageType);
        return woPackageTypeMapper.toDto(woPackageType);
    }

    /**
     * Get all the woPackageTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<WoPackageTypeDTO> findAll() {
        log.debug("Request to get all WoPackageTypes");
        return woPackageTypeRepository.findAll().stream()
            .map(woPackageTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one woPackageType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoPackageTypeDTO> findOne(Long id) {
        log.debug("Request to get WoPackageType : {}", id);
        return woPackageTypeRepository.findById(id)
            .map(woPackageTypeMapper::toDto);
    }

    /**
     * Delete the woPackageType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoPackageType : {}", id);
        woPackageTypeRepository.deleteById(id);
    }
}
