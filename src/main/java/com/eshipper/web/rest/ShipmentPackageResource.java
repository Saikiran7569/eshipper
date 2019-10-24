package com.eshipper.web.rest;

import com.eshipper.service.ShipmentPackageService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ShipmentPackageDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.ShipmentPackage}.
 */
@RestController
@RequestMapping("/api")
public class ShipmentPackageResource {

    private final Logger log = LoggerFactory.getLogger(ShipmentPackageResource.class);

    private static final String ENTITY_NAME = "shipmentPackage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShipmentPackageService shipmentPackageService;

    public ShipmentPackageResource(ShipmentPackageService shipmentPackageService) {
        this.shipmentPackageService = shipmentPackageService;
    }

    /**
     * {@code POST  /shipment-packages} : Create a new shipmentPackage.
     *
     * @param shipmentPackageDTO the shipmentPackageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shipmentPackageDTO, or with status {@code 400 (Bad Request)} if the shipmentPackage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shipment-packages")
    public ResponseEntity<ShipmentPackageDTO> createShipmentPackage(@Valid @RequestBody ShipmentPackageDTO shipmentPackageDTO) throws URISyntaxException {
        log.debug("REST request to save ShipmentPackage : {}", shipmentPackageDTO);
        if (shipmentPackageDTO.getId() != null) {
            throw new BadRequestAlertException("A new shipmentPackage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShipmentPackageDTO result = shipmentPackageService.save(shipmentPackageDTO);
        return ResponseEntity.created(new URI("/api/shipment-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shipment-packages} : Updates an existing shipmentPackage.
     *
     * @param shipmentPackageDTO the shipmentPackageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shipmentPackageDTO,
     * or with status {@code 400 (Bad Request)} if the shipmentPackageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shipmentPackageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shipment-packages")
    public ResponseEntity<ShipmentPackageDTO> updateShipmentPackage(@Valid @RequestBody ShipmentPackageDTO shipmentPackageDTO) throws URISyntaxException {
        log.debug("REST request to update ShipmentPackage : {}", shipmentPackageDTO);
        if (shipmentPackageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShipmentPackageDTO result = shipmentPackageService.save(shipmentPackageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shipmentPackageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shipment-packages} : get all the shipmentPackages.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shipmentPackages in body.
     */
    @GetMapping("/shipment-packages")
    public ResponseEntity<List<ShipmentPackageDTO>> getAllShipmentPackages(Pageable pageable) {
        log.debug("REST request to get a page of ShipmentPackages");
        Page<ShipmentPackageDTO> page = shipmentPackageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /shipment-packages/:id} : get the "id" shipmentPackage.
     *
     * @param id the id of the shipmentPackageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shipmentPackageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shipment-packages/{id}")
    public ResponseEntity<ShipmentPackageDTO> getShipmentPackage(@PathVariable Long id) {
        log.debug("REST request to get ShipmentPackage : {}", id);
        Optional<ShipmentPackageDTO> shipmentPackageDTO = shipmentPackageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shipmentPackageDTO);
    }

    /**
     * {@code DELETE  /shipment-packages/:id} : delete the "id" shipmentPackage.
     *
     * @param id the id of the shipmentPackageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shipment-packages/{id}")
    public ResponseEntity<Void> deleteShipmentPackage(@PathVariable Long id) {
        log.debug("REST request to delete ShipmentPackage : {}", id);
        shipmentPackageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
