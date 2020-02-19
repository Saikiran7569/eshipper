package com.eshipper.service;

import com.eshipper.service.dto.User11DTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.User11}.
 */
public interface User11Service {

    /**
     * Save a user11.
     *
     * @param user11DTO the entity to save.
     * @return the persisted entity.
     */
    User11DTO save(User11DTO user11DTO);

    /**
     * Get all the user11S.
     *
     * @return the list of entities.
     */
    List<User11DTO> findAll();

    /**
     * Get the "id" user11.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<User11DTO> findOne(Long id);

    /**
     * Delete the "id" user11.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
