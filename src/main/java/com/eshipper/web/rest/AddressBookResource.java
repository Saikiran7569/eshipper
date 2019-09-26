package com.eshipper.web.rest;

import com.eshipper.domain.AddressBook;
import com.eshipper.repository.AddressBookRepository;
import com.eshipper.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.AddressBook}.
 */
@RestController
@RequestMapping("/api")
public class AddressBookResource {

    private final Logger log = LoggerFactory.getLogger(AddressBookResource.class);

    private static final String ENTITY_NAME = "addressBook";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AddressBookRepository addressBookRepository;

    public AddressBookResource(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    /**
     * {@code POST  /address-books} : Create a new addressBook.
     *
     * @param addressBook the addressBook to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new addressBook, or with status {@code 400 (Bad Request)} if the addressBook has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/address-books")
    public ResponseEntity<AddressBook> createAddressBook(@Valid @RequestBody AddressBook addressBook) throws URISyntaxException {
        log.debug("REST request to save AddressBook : {}", addressBook);
        if (addressBook.getId() != null) {
            throw new BadRequestAlertException("A new addressBook cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AddressBook result = addressBookRepository.save(addressBook);
        return ResponseEntity.created(new URI("/api/address-books/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /address-books} : Updates an existing addressBook.
     *
     * @param addressBook the addressBook to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated addressBook,
     * or with status {@code 400 (Bad Request)} if the addressBook is not valid,
     * or with status {@code 500 (Internal Server Error)} if the addressBook couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/address-books")
    public ResponseEntity<AddressBook> updateAddressBook(@Valid @RequestBody AddressBook addressBook) throws URISyntaxException {
        log.debug("REST request to update AddressBook : {}", addressBook);
        if (addressBook.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AddressBook result = addressBookRepository.save(addressBook);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, addressBook.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /address-books} : get all the addressBooks.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of addressBooks in body.
     */
    @GetMapping("/address-books")
    public List<AddressBook> getAllAddressBooks() {
        log.debug("REST request to get all AddressBooks");
        return addressBookRepository.findAll();
    }

    /**
     * {@code GET  /address-books/:id} : get the "id" addressBook.
     *
     * @param id the id of the addressBook to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the addressBook, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/address-books/{id}")
    public ResponseEntity<AddressBook> getAddressBook(@PathVariable Long id) {
        log.debug("REST request to get AddressBook : {}", id);
        Optional<AddressBook> addressBook = addressBookRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(addressBook);
    }

    /**
     * {@code DELETE  /address-books/:id} : delete the "id" addressBook.
     *
     * @param id the id of the addressBook to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/address-books/{id}")
    public ResponseEntity<Void> deleteAddressBook(@PathVariable Long id) {
        log.debug("REST request to delete AddressBook : {}", id);
        addressBookRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
