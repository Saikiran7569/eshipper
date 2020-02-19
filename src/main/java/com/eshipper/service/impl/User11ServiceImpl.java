package com.eshipper.service.impl;

import com.eshipper.service.User11Service;
import com.eshipper.domain.User11;
import com.eshipper.repository.User11Repository;
import com.eshipper.service.dto.User11DTO;
import com.eshipper.service.mapper.User11Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link User11}.
 */
@Service
@Transactional
public class User11ServiceImpl implements User11Service {

    private final Logger log = LoggerFactory.getLogger(User11ServiceImpl.class);

    private final User11Repository user11Repository;

    private final User11Mapper user11Mapper;

    public User11ServiceImpl(User11Repository user11Repository, User11Mapper user11Mapper) {
        this.user11Repository = user11Repository;
        this.user11Mapper = user11Mapper;
    }

    /**
     * Save a user11.
     *
     * @param user11DTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public User11DTO save(User11DTO user11DTO) {
        log.debug("Request to save User11 : {}", user11DTO);
        User11 user11 = user11Mapper.toEntity(user11DTO);
        user11 = user11Repository.save(user11);
        return user11Mapper.toDto(user11);
    }

    /**
     * Get all the user11S.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User11DTO> findAll() {
        log.debug("Request to get all User11S");
        return user11Repository.findAll().stream()
            .map(user11Mapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one user11 by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<User11DTO> findOne(Long id) {
        log.debug("Request to get User11 : {}", id);
        return user11Repository.findById(id)
            .map(user11Mapper::toDto);
    }

    /**
     * Delete the user11 by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete User11 : {}", id);
        user11Repository.deleteById(id);
    }
}
