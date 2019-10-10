package com.eshipper.service;

import com.eshipper.service.dto.AddressBookDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.AddressBook}.
 */
public interface AddressBookService {

    /**
     * Save a addressBook.
     *
     * @param addressBookDTO the entity to save.
     * @return the persisted entity.
     */
    AddressBookDTO save(AddressBookDTO addressBookDTO);

    /**
     * Get all the addressBooks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AddressBookDTO> findAll(Pageable pageable);


    /**
     * Get the "id" addressBook.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AddressBookDTO> findOne(Long id);

    /**
     * Delete the "id" addressBook.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
