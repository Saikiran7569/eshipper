package com.eshipper.web.rest;

import com.eshipper.service.PalletTypeService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.PalletTypeDTO;

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
 * REST controller for managing {@link com.eshipper.domain.PalletType}.
 */
@RestController
@RequestMapping("/api")
public class PalletTypeResource {

    private final Logger log = LoggerFactory.getLogger(PalletTypeResource.class);

    private static final String ENTITY_NAME = "palletType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PalletTypeService palletTypeService;

    public PalletTypeResource(PalletTypeService palletTypeService) {
        this.palletTypeService = palletTypeService;
    }

    /**
     * {@code POST  /pallet-types} : Create a new palletType.
     *
     * @param palletTypeDTO the palletTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new palletTypeDTO, or with status {@code 400 (Bad Request)} if the palletType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pallet-types")
    public ResponseEntity<PalletTypeDTO> createPalletType(@RequestBody PalletTypeDTO palletTypeDTO) throws URISyntaxException {
        log.debug("REST request to save PalletType : {}", palletTypeDTO);
        if (palletTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new palletType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PalletTypeDTO result = palletTypeService.save(palletTypeDTO);
        return ResponseEntity.created(new URI("/api/pallet-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pallet-types} : Updates an existing palletType.
     *
     * @param palletTypeDTO the palletTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated palletTypeDTO,
     * or with status {@code 400 (Bad Request)} if the palletTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the palletTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pallet-types")
    public ResponseEntity<PalletTypeDTO> updatePalletType(@RequestBody PalletTypeDTO palletTypeDTO) throws URISyntaxException {
        log.debug("REST request to update PalletType : {}", palletTypeDTO);
        if (palletTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PalletTypeDTO result = palletTypeService.save(palletTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, palletTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pallet-types} : get all the palletTypes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of palletTypes in body.
     */
    @GetMapping("/pallet-types")
    public ResponseEntity<List<PalletTypeDTO>> getAllPalletTypes(Pageable pageable) {
        log.debug("REST request to get a page of PalletTypes");
        Page<PalletTypeDTO> page = palletTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pallet-types/:id} : get the "id" palletType.
     *
     * @param id the id of the palletTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the palletTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pallet-types/{id}")
    public ResponseEntity<PalletTypeDTO> getPalletType(@PathVariable Long id) {
        log.debug("REST request to get PalletType : {}", id);
        Optional<PalletTypeDTO> palletTypeDTO = palletTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(palletTypeDTO);
    }

    /**
     * {@code DELETE  /pallet-types/:id} : delete the "id" palletType.
     *
     * @param id the id of the palletTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pallet-types/{id}")
    public ResponseEntity<Void> deletePalletType(@PathVariable Long id) {
        log.debug("REST request to delete PalletType : {}", id);
        palletTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
