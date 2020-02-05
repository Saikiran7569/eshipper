package com.eshipper.web.rest;

import com.eshipper.service.MonthlyShipmentsService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.MonthlyShipmentsDTO;

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
 * REST controller for managing {@link com.eshipper.domain.MonthlyShipments}.
 */
@RestController
@RequestMapping("/api")
public class MonthlyShipmentsResource {

    private final Logger log = LoggerFactory.getLogger(MonthlyShipmentsResource.class);

    private static final String ENTITY_NAME = "monthlyShipments";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MonthlyShipmentsService monthlyShipmentsService;

    public MonthlyShipmentsResource(MonthlyShipmentsService monthlyShipmentsService) {
        this.monthlyShipmentsService = monthlyShipmentsService;
    }

    /**
     * {@code POST  /monthly-shipments} : Create a new monthlyShipments.
     *
     * @param monthlyShipmentsDTO the monthlyShipmentsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new monthlyShipmentsDTO, or with status {@code 400 (Bad Request)} if the monthlyShipments has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/monthly-shipments")
    public ResponseEntity<MonthlyShipmentsDTO> createMonthlyShipments(@RequestBody MonthlyShipmentsDTO monthlyShipmentsDTO) throws URISyntaxException {
        log.debug("REST request to save MonthlyShipments : {}", monthlyShipmentsDTO);
        if (monthlyShipmentsDTO.getId() != null) {
            throw new BadRequestAlertException("A new monthlyShipments cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MonthlyShipmentsDTO result = monthlyShipmentsService.save(monthlyShipmentsDTO);
        return ResponseEntity.created(new URI("/api/monthly-shipments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /monthly-shipments} : Updates an existing monthlyShipments.
     *
     * @param monthlyShipmentsDTO the monthlyShipmentsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated monthlyShipmentsDTO,
     * or with status {@code 400 (Bad Request)} if the monthlyShipmentsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the monthlyShipmentsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/monthly-shipments")
    public ResponseEntity<MonthlyShipmentsDTO> updateMonthlyShipments(@RequestBody MonthlyShipmentsDTO monthlyShipmentsDTO) throws URISyntaxException {
        log.debug("REST request to update MonthlyShipments : {}", monthlyShipmentsDTO);
        if (monthlyShipmentsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MonthlyShipmentsDTO result = monthlyShipmentsService.save(monthlyShipmentsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, monthlyShipmentsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /monthly-shipments} : get all the monthlyShipments.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of monthlyShipments in body.
     */
    @GetMapping("/monthly-shipments")
    public List<MonthlyShipmentsDTO> getAllMonthlyShipments() {
        log.debug("REST request to get all MonthlyShipments");
        return monthlyShipmentsService.findAll();
    }

    /**
     * {@code GET  /monthly-shipments/:id} : get the "id" monthlyShipments.
     *
     * @param id the id of the monthlyShipmentsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the monthlyShipmentsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/monthly-shipments/{id}")
    public ResponseEntity<MonthlyShipmentsDTO> getMonthlyShipments(@PathVariable Long id) {
        log.debug("REST request to get MonthlyShipments : {}", id);
        Optional<MonthlyShipmentsDTO> monthlyShipmentsDTO = monthlyShipmentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(monthlyShipmentsDTO);
    }

    /**
     * {@code DELETE  /monthly-shipments/:id} : delete the "id" monthlyShipments.
     *
     * @param id the id of the monthlyShipmentsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/monthly-shipments/{id}")
    public ResponseEntity<Void> deleteMonthlyShipments(@PathVariable Long id) {
        log.debug("REST request to delete MonthlyShipments : {}", id);
        monthlyShipmentsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
