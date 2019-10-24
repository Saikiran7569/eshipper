package com.eshipper.service.impl;

import com.eshipper.service.PalletTypeService;
import com.eshipper.domain.PalletType;
import com.eshipper.repository.PalletTypeRepository;
import com.eshipper.service.dto.PalletTypeDTO;
import com.eshipper.service.mapper.PalletTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PalletType}.
 */
@Service
@Transactional
public class PalletTypeServiceImpl implements PalletTypeService {

    private final Logger log = LoggerFactory.getLogger(PalletTypeServiceImpl.class);

    private final PalletTypeRepository palletTypeRepository;

    private final PalletTypeMapper palletTypeMapper;

    public PalletTypeServiceImpl(PalletTypeRepository palletTypeRepository, PalletTypeMapper palletTypeMapper) {
        this.palletTypeRepository = palletTypeRepository;
        this.palletTypeMapper = palletTypeMapper;
    }

    /**
     * Save a palletType.
     *
     * @param palletTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PalletTypeDTO save(PalletTypeDTO palletTypeDTO) {
        log.debug("Request to save PalletType : {}", palletTypeDTO);
        PalletType palletType = palletTypeMapper.toEntity(palletTypeDTO);
        palletType = palletTypeRepository.save(palletType);
        return palletTypeMapper.toDto(palletType);
    }

    /**
     * Get all the palletTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PalletTypeDTO> findAll() {
        log.debug("Request to get all PalletTypes");
        return palletTypeRepository.findAll().stream()
            .map(palletTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one palletType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PalletTypeDTO> findOne(Long id) {
        log.debug("Request to get PalletType : {}", id);
        return palletTypeRepository.findById(id)
            .map(palletTypeMapper::toDto);
    }

    /**
     * Delete the palletType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PalletType : {}", id);
        palletTypeRepository.deleteById(id);
    }
}
