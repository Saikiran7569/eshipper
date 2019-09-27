package com.eshipper.service.impl;

import com.eshipper.service.AddressBookService;
import com.eshipper.domain.AddressBook;
import com.eshipper.repository.AddressBookRepository;
import com.eshipper.service.dto.AddressBookDTO;
import com.eshipper.service.mapper.AddressBookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AddressBook}.
 */
@Service
@Transactional
public class AddressBookServiceImpl implements AddressBookService {

    private final Logger log = LoggerFactory.getLogger(AddressBookServiceImpl.class);

    private final AddressBookRepository addressBookRepository;

    private final AddressBookMapper addressBookMapper;

    public AddressBookServiceImpl(AddressBookRepository addressBookRepository, AddressBookMapper addressBookMapper) {
        this.addressBookRepository = addressBookRepository;
        this.addressBookMapper = addressBookMapper;
    }

    /**
     * Save a addressBook.
     *
     * @param addressBookDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AddressBookDTO save(AddressBookDTO addressBookDTO) {
        log.debug("Request to save AddressBook : {}", addressBookDTO);
        AddressBook addressBook = addressBookMapper.toEntity(addressBookDTO);
        addressBook = addressBookRepository.save(addressBook);
        return addressBookMapper.toDto(addressBook);
    }

    /**
     * Get all the addressBooks.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AddressBookDTO> findAll() {
        log.debug("Request to get all AddressBooks");
        return addressBookRepository.findAll().stream()
            .map(addressBookMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one addressBook by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AddressBookDTO> findOne(Long id) {
        log.debug("Request to get AddressBook : {}", id);
        return addressBookRepository.findById(id)
            .map(addressBookMapper::toDto);
    }

    /**
     * Delete the addressBook by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AddressBook : {}", id);
        addressBookRepository.deleteById(id);
    }
}
