package com.eshipper.web.rest;

import com.eshipper.service.BatchServiceTypeService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.BatchServiceTypeDTO;

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
 * REST controller for managing {@link com.eshipper.domain.BatchServiceType}.
 */
@RestController
@RequestMapping("/api")
public class BatchServiceTypeResource {

    private final Logger log = LoggerFactory.getLogger(BatchServiceTypeResource.class);

    private static final String ENTITY_NAME = "batchServiceType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BatchServiceTypeService batchServiceTypeService;

    public BatchServiceTypeResource(BatchServiceTypeService batchServiceTypeService) {
        this.batchServiceTypeService = batchServiceTypeService;
    }

    /**
     * {@code POST  /batch-service-types} : Create a new batchServiceType.
     *
     * @param batchServiceTypeDTO the batchServiceTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new batchServiceTypeDTO, or with status {@code 400 (Bad Request)} if the batchServiceType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/batch-service-types")
    public ResponseEntity<BatchServiceTypeDTO> createBatchServiceType(@RequestBody BatchServiceTypeDTO batchServiceTypeDTO) throws URISyntaxException {
        log.debug("REST request to save BatchServiceType : {}", batchServiceTypeDTO);
        if (batchServiceTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new batchServiceType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BatchServiceTypeDTO result = batchServiceTypeService.save(batchServiceTypeDTO);
        return ResponseEntity.created(new URI("/api/batch-service-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /batch-service-types} : Updates an existing batchServiceType.
     *
     * @param batchServiceTypeDTO the batchServiceTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated batchServiceTypeDTO,
     * or with status {@code 400 (Bad Request)} if the batchServiceTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the batchServiceTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/batch-service-types")
    public ResponseEntity<BatchServiceTypeDTO> updateBatchServiceType(@RequestBody BatchServiceTypeDTO batchServiceTypeDTO) throws URISyntaxException {
        log.debug("REST request to update BatchServiceType : {}", batchServiceTypeDTO);
        if (batchServiceTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BatchServiceTypeDTO result = batchServiceTypeService.save(batchServiceTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, batchServiceTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /batch-service-types} : get all the batchServiceTypes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of batchServiceTypes in body.
     */
    @GetMapping("/batch-service-types")
    public List<BatchServiceTypeDTO> getAllBatchServiceTypes() {
        log.debug("REST request to get all BatchServiceTypes");
        return batchServiceTypeService.findAll();
    }

    /**
     * {@code GET  /batch-service-types/:id} : get the "id" batchServiceType.
     *
     * @param id the id of the batchServiceTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the batchServiceTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/batch-service-types/{id}")
    public ResponseEntity<BatchServiceTypeDTO> getBatchServiceType(@PathVariable Long id) {
        log.debug("REST request to get BatchServiceType : {}", id);
        Optional<BatchServiceTypeDTO> batchServiceTypeDTO = batchServiceTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(batchServiceTypeDTO);
    }

    /**
     * {@code DELETE  /batch-service-types/:id} : delete the "id" batchServiceType.
     *
     * @param id the id of the batchServiceTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/batch-service-types/{id}")
    public ResponseEntity<Void> deleteBatchServiceType(@PathVariable Long id) {
        log.debug("REST request to delete BatchServiceType : {}", id);
        batchServiceTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
