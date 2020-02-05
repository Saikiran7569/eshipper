package com.eshipper.web.rest;

import com.eshipper.service.CustomsTaxBillToService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.CustomsTaxBillToDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.CustomsTaxBillTo}.
 */
@RestController
@RequestMapping("/api")
public class CustomsTaxBillToResource {

    private final Logger log = LoggerFactory.getLogger(CustomsTaxBillToResource.class);

    private static final String ENTITY_NAME = "customsTaxBillTo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomsTaxBillToService customsTaxBillToService;

    public CustomsTaxBillToResource(CustomsTaxBillToService customsTaxBillToService) {
        this.customsTaxBillToService = customsTaxBillToService;
    }

    /**
     * {@code POST  /customs-tax-bill-tos} : Create a new customsTaxBillTo.
     *
     * @param customsTaxBillToDTO the customsTaxBillToDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customsTaxBillToDTO, or with status {@code 400 (Bad Request)} if the customsTaxBillTo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customs-tax-bill-tos")
    public ResponseEntity<CustomsTaxBillToDTO> createCustomsTaxBillTo(@RequestBody CustomsTaxBillToDTO customsTaxBillToDTO) throws URISyntaxException {
        log.debug("REST request to save CustomsTaxBillTo : {}", customsTaxBillToDTO);
        if (customsTaxBillToDTO.getId() != null) {
            throw new BadRequestAlertException("A new customsTaxBillTo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomsTaxBillToDTO result = customsTaxBillToService.save(customsTaxBillToDTO);
        return ResponseEntity.created(new URI("/api/customs-tax-bill-tos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customs-tax-bill-tos} : Updates an existing customsTaxBillTo.
     *
     * @param customsTaxBillToDTO the customsTaxBillToDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customsTaxBillToDTO,
     * or with status {@code 400 (Bad Request)} if the customsTaxBillToDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customsTaxBillToDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customs-tax-bill-tos")
    public ResponseEntity<CustomsTaxBillToDTO> updateCustomsTaxBillTo(@RequestBody CustomsTaxBillToDTO customsTaxBillToDTO) throws URISyntaxException {
        log.debug("REST request to update CustomsTaxBillTo : {}", customsTaxBillToDTO);
        if (customsTaxBillToDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomsTaxBillToDTO result = customsTaxBillToService.save(customsTaxBillToDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customsTaxBillToDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /customs-tax-bill-tos} : get all the customsTaxBillTos.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customsTaxBillTos in body.
     */
    @GetMapping("/customs-tax-bill-tos")
    public List<CustomsTaxBillToDTO> getAllCustomsTaxBillTos() {
        log.debug("REST request to get all CustomsTaxBillTos");
        return customsTaxBillToService.findAll();
    }

    /**
     * {@code GET  /customs-tax-bill-tos/:id} : get the "id" customsTaxBillTo.
     *
     * @param id the id of the customsTaxBillToDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customsTaxBillToDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customs-tax-bill-tos/{id}")
    public ResponseEntity<CustomsTaxBillToDTO> getCustomsTaxBillTo(@PathVariable Long id) {
        log.debug("REST request to get CustomsTaxBillTo : {}", id);
        Optional<CustomsTaxBillToDTO> customsTaxBillToDTO = customsTaxBillToService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customsTaxBillToDTO);
    }

    /**
     * {@code DELETE  /customs-tax-bill-tos/:id} : delete the "id" customsTaxBillTo.
     *
     * @param id the id of the customsTaxBillToDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customs-tax-bill-tos/{id}")
    public ResponseEntity<Void> deleteCustomsTaxBillTo(@PathVariable Long id) {
        log.debug("REST request to delete CustomsTaxBillTo : {}", id);
        customsTaxBillToService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
