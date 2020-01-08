package com.eshipper.web.rest;

import com.eshipper.service.SuppliesService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.SuppliesDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.Supplies}.
 */
@RestController
@RequestMapping("/api")
public class SuppliesResource {

    private final Logger log = LoggerFactory.getLogger(SuppliesResource.class);

    private static final String ENTITY_NAME = "supplies";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SuppliesService suppliesService;

    public SuppliesResource(SuppliesService suppliesService) {
        this.suppliesService = suppliesService;
    }

    /**
     * {@code POST  /supplies} : Create a new supplies.
     *
     * @param suppliesDTO the suppliesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new suppliesDTO, or with status {@code 400 (Bad Request)} if the supplies has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/supplies")
    public ResponseEntity<SuppliesDTO> createSupplies(@RequestBody SuppliesDTO suppliesDTO) throws URISyntaxException {
        log.debug("REST request to save Supplies : {}", suppliesDTO);
        if (suppliesDTO.getId() != null) {
            throw new BadRequestAlertException("A new supplies cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SuppliesDTO result = suppliesService.save(suppliesDTO);
        return ResponseEntity.created(new URI("/api/supplies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /supplies} : Updates an existing supplies.
     *
     * @param suppliesDTO the suppliesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated suppliesDTO,
     * or with status {@code 400 (Bad Request)} if the suppliesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the suppliesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/supplies")
    public ResponseEntity<SuppliesDTO> updateSupplies(@RequestBody SuppliesDTO suppliesDTO) throws URISyntaxException {
        log.debug("REST request to update Supplies : {}", suppliesDTO);
        if (suppliesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SuppliesDTO result = suppliesService.save(suppliesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, suppliesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /supplies} : get all the supplies.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of supplies in body.
     */
    @GetMapping("/supplies")
    public ResponseEntity<List<SuppliesDTO>> getAllSupplies(Pageable pageable) {
        log.debug("REST request to get a page of Supplies");
        Page<SuppliesDTO> page = suppliesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /supplies/:id} : get the "id" supplies.
     *
     * @param id the id of the suppliesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the suppliesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/supplies/{id}")
    public ResponseEntity<SuppliesDTO> getSupplies(@PathVariable Long id) {
        log.debug("REST request to get Supplies : {}", id);
        Optional<SuppliesDTO> suppliesDTO = suppliesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(suppliesDTO);
    }

    /**
     * {@code DELETE  /supplies/:id} : delete the "id" supplies.
     *
     * @param id the id of the suppliesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/supplies/{id}")
    public ResponseEntity<Void> deleteSupplies(@PathVariable Long id) {
        log.debug("REST request to delete Supplies : {}", id);
        suppliesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
