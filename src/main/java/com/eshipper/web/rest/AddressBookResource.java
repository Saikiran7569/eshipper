package com.eshipper.web.rest;

import com.eshipper.service.AddressBookService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.AddressBookDTO;

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

    private final AddressBookService addressBookService;

    public AddressBookResource(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    /**
     * {@code POST  /address-books} : Create a new addressBook.
     *
     * @param addressBookDTO the addressBookDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new addressBookDTO, or with status {@code 400 (Bad Request)} if the addressBook has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/address-books")
    public ResponseEntity<AddressBookDTO> createAddressBook(@Valid @RequestBody AddressBookDTO addressBookDTO) throws URISyntaxException {
        log.debug("REST request to save AddressBook : {}", addressBookDTO);
        if (addressBookDTO.getId() != null) {
            throw new BadRequestAlertException("A new addressBook cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AddressBookDTO result = addressBookService.save(addressBookDTO);
        return ResponseEntity.created(new URI("/api/address-books/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /address-books} : Updates an existing addressBook.
     *
     * @param addressBookDTO the addressBookDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated addressBookDTO,
     * or with status {@code 400 (Bad Request)} if the addressBookDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the addressBookDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/address-books")
    public ResponseEntity<AddressBookDTO> updateAddressBook(@Valid @RequestBody AddressBookDTO addressBookDTO) throws URISyntaxException {
        log.debug("REST request to update AddressBook : {}", addressBookDTO);
        if (addressBookDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AddressBookDTO result = addressBookService.save(addressBookDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, addressBookDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /address-books} : get all the addressBooks.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of addressBooks in body.
     */
    @GetMapping("/address-books")
    public List<AddressBookDTO> getAllAddressBooks() {
        log.debug("REST request to get all AddressBooks");
        return addressBookService.findAll();
    }

    /**
     * {@code GET  /address-books/:id} : get the "id" addressBook.
     *
     * @param id the id of the addressBookDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the addressBookDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/address-books/{id}")
    public ResponseEntity<AddressBookDTO> getAddressBook(@PathVariable Long id) {
        log.debug("REST request to get AddressBook : {}", id);
        Optional<AddressBookDTO> addressBookDTO = addressBookService.findOne(id);
        return ResponseUtil.wrapOrNotFound(addressBookDTO);
    }

    /**
     * {@code DELETE  /address-books/:id} : delete the "id" addressBook.
     *
     * @param id the id of the addressBookDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/address-books/{id}")
    public ResponseEntity<Void> deleteAddressBook(@PathVariable Long id) {
        log.debug("REST request to delete AddressBook : {}", id);
        addressBookService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
