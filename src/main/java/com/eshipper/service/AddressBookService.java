package com.eshipper.service;

import com.eshipper.service.dto.AddressBookDTO;

import java.util.List;
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
     * @return the list of entities.
     */
    List<AddressBookDTO> findAll();


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
