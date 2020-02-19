package com.eshipper.web.rest;

import com.eshipper.service.SuppliesOrdersService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.SuppliesOrdersDTO;

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
 * REST controller for managing {@link com.eshipper.domain.SuppliesOrders}.
 */
@RestController
@RequestMapping("/api")
public class SuppliesOrdersResource {

    private final Logger log = LoggerFactory.getLogger(SuppliesOrdersResource.class);

    private static final String ENTITY_NAME = "suppliesOrders";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SuppliesOrdersService suppliesOrdersService;

    public SuppliesOrdersResource(SuppliesOrdersService suppliesOrdersService) {
        this.suppliesOrdersService = suppliesOrdersService;
    }

    /**
     * {@code POST  /supplies-orders} : Create a new suppliesOrders.
     *
     * @param suppliesOrdersDTO the suppliesOrdersDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new suppliesOrdersDTO, or with status {@code 400 (Bad Request)} if the suppliesOrders has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/supplies-orders")
    public ResponseEntity<SuppliesOrdersDTO> createSuppliesOrders(@RequestBody SuppliesOrdersDTO suppliesOrdersDTO) throws URISyntaxException {
        log.debug("REST request to save SuppliesOrders : {}", suppliesOrdersDTO);
        if (suppliesOrdersDTO.getId() != null) {
            throw new BadRequestAlertException("A new suppliesOrders cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SuppliesOrdersDTO result = suppliesOrdersService.save(suppliesOrdersDTO);
        return ResponseEntity.created(new URI("/api/supplies-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /supplies-orders} : Updates an existing suppliesOrders.
     *
     * @param suppliesOrdersDTO the suppliesOrdersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated suppliesOrdersDTO,
     * or with status {@code 400 (Bad Request)} if the suppliesOrdersDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the suppliesOrdersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/supplies-orders")
    public ResponseEntity<SuppliesOrdersDTO> updateSuppliesOrders(@RequestBody SuppliesOrdersDTO suppliesOrdersDTO) throws URISyntaxException {
        log.debug("REST request to update SuppliesOrders : {}", suppliesOrdersDTO);
        if (suppliesOrdersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SuppliesOrdersDTO result = suppliesOrdersService.save(suppliesOrdersDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, suppliesOrdersDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /supplies-orders} : get all the suppliesOrders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of suppliesOrders in body.
     */
    @GetMapping("/supplies-orders")
    public List<SuppliesOrdersDTO> getAllSuppliesOrders() {
        log.debug("REST request to get all SuppliesOrders");
        return suppliesOrdersService.findAll();
    }

    /**
     * {@code GET  /supplies-orders/:id} : get the "id" suppliesOrders.
     *
     * @param id the id of the suppliesOrdersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the suppliesOrdersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/supplies-orders/{id}")
    public ResponseEntity<SuppliesOrdersDTO> getSuppliesOrders(@PathVariable Long id) {
        log.debug("REST request to get SuppliesOrders : {}", id);
        Optional<SuppliesOrdersDTO> suppliesOrdersDTO = suppliesOrdersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(suppliesOrdersDTO);
    }

    /**
     * {@code DELETE  /supplies-orders/:id} : delete the "id" suppliesOrders.
     *
     * @param id the id of the suppliesOrdersDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/supplies-orders/{id}")
    public ResponseEntity<Void> deleteSuppliesOrders(@PathVariable Long id) {
        log.debug("REST request to delete SuppliesOrders : {}", id);
        suppliesOrdersService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
