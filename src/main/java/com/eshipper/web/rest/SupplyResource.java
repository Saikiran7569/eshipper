package com.eshipper.web.rest;

import com.eshipper.service.SupplyService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.SupplyDTO;

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
 * REST controller for managing {@link com.eshipper.domain.Supply}.
 */
@RestController
@RequestMapping("/api")
public class SupplyResource {

    private final Logger log = LoggerFactory.getLogger(SupplyResource.class);

    private static final String ENTITY_NAME = "supply";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SupplyService supplyService;

    public SupplyResource(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    /**
     * {@code POST  /supplies} : Create a new supply.
     *
     * @param supplyDTO the supplyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new supplyDTO, or with status {@code 400 (Bad Request)} if the supply has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/supplies")
    public ResponseEntity<SupplyDTO> createSupply(@RequestBody SupplyDTO supplyDTO) throws URISyntaxException {
        log.debug("REST request to save Supply : {}", supplyDTO);
        if (supplyDTO.getId() != null) {
            throw new BadRequestAlertException("A new supply cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SupplyDTO result = supplyService.save(supplyDTO);
        return ResponseEntity.created(new URI("/api/supplies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /supplies} : Updates an existing supply.
     *
     * @param supplyDTO the supplyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supplyDTO,
     * or with status {@code 400 (Bad Request)} if the supplyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the supplyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/supplies")
    public ResponseEntity<SupplyDTO> updateSupply(@RequestBody SupplyDTO supplyDTO) throws URISyntaxException {
        log.debug("REST request to update Supply : {}", supplyDTO);
        if (supplyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SupplyDTO result = supplyService.save(supplyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, supplyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /supplies} : get all the supplies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of supplies in body.
     */
    @GetMapping("/supplies")
    public List<SupplyDTO> getAllSupplies() {
        log.debug("REST request to get all Supplies");
        return supplyService.findAll();
    }

    /**
     * {@code GET  /supplies/:id} : get the "id" supply.
     *
     * @param id the id of the supplyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the supplyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/supplies/{id}")
    public ResponseEntity<SupplyDTO> getSupply(@PathVariable Long id) {
        log.debug("REST request to get Supply : {}", id);
        Optional<SupplyDTO> supplyDTO = supplyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(supplyDTO);
    }

    /**
     * {@code DELETE  /supplies/:id} : delete the "id" supply.
     *
     * @param id the id of the supplyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/supplies/{id}")
    public ResponseEntity<Void> deleteSupply(@PathVariable Long id) {
        log.debug("REST request to delete Supply : {}", id);
        supplyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
