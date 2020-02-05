package com.eshipper.service.impl;

import com.eshipper.service.CustomsTaxBillToService;
import com.eshipper.domain.CustomsTaxBillTo;
import com.eshipper.repository.CustomsTaxBillToRepository;
import com.eshipper.service.dto.CustomsTaxBillToDTO;
import com.eshipper.service.mapper.CustomsTaxBillToMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CustomsTaxBillTo}.
 */
@Service
@Transactional
public class CustomsTaxBillToServiceImpl implements CustomsTaxBillToService {

    private final Logger log = LoggerFactory.getLogger(CustomsTaxBillToServiceImpl.class);

    private final CustomsTaxBillToRepository customsTaxBillToRepository;

    private final CustomsTaxBillToMapper customsTaxBillToMapper;

    public CustomsTaxBillToServiceImpl(CustomsTaxBillToRepository customsTaxBillToRepository, CustomsTaxBillToMapper customsTaxBillToMapper) {
        this.customsTaxBillToRepository = customsTaxBillToRepository;
        this.customsTaxBillToMapper = customsTaxBillToMapper;
    }

    /**
     * Save a customsTaxBillTo.
     *
     * @param customsTaxBillToDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CustomsTaxBillToDTO save(CustomsTaxBillToDTO customsTaxBillToDTO) {
        log.debug("Request to save CustomsTaxBillTo : {}", customsTaxBillToDTO);
        CustomsTaxBillTo customsTaxBillTo = customsTaxBillToMapper.toEntity(customsTaxBillToDTO);
        customsTaxBillTo = customsTaxBillToRepository.save(customsTaxBillTo);
        return customsTaxBillToMapper.toDto(customsTaxBillTo);
    }

    /**
     * Get all the customsTaxBillTos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomsTaxBillToDTO> findAll() {
        log.debug("Request to get all CustomsTaxBillTos");
        return customsTaxBillToRepository.findAll().stream()
            .map(customsTaxBillToMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one customsTaxBillTo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomsTaxBillToDTO> findOne(Long id) {
        log.debug("Request to get CustomsTaxBillTo : {}", id);
        return customsTaxBillToRepository.findById(id)
            .map(customsTaxBillToMapper::toDto);
    }

    /**
     * Delete the customsTaxBillTo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomsTaxBillTo : {}", id);
        customsTaxBillToRepository.deleteById(id);
    }
}
