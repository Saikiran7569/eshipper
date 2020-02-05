package com.eshipper.web.rest;

import com.eshipper.service.IndustryService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.IndustryDTO;

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
 * REST controller for managing {@link com.eshipper.domain.Industry}.
 */
@RestController
@RequestMapping("/api")
public class IndustryResource {

    private final Logger log = LoggerFactory.getLogger(IndustryResource.class);

    private static final String ENTITY_NAME = "industry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IndustryService industryService;

    public IndustryResource(IndustryService industryService) {
        this.industryService = industryService;
    }

    /**
     * {@code POST  /industries} : Create a new industry.
     *
     * @param industryDTO the industryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new industryDTO, or with status {@code 400 (Bad Request)} if the industry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/industries")
    public ResponseEntity<IndustryDTO> createIndustry(@RequestBody IndustryDTO industryDTO) throws URISyntaxException {
        log.debug("REST request to save Industry : {}", industryDTO);
        if (industryDTO.getId() != null) {
            throw new BadRequestAlertException("A new industry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IndustryDTO result = industryService.save(industryDTO);
        return ResponseEntity.created(new URI("/api/industries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /industries} : Updates an existing industry.
     *
     * @param industryDTO the industryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated industryDTO,
     * or with status {@code 400 (Bad Request)} if the industryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the industryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/industries")
    public ResponseEntity<IndustryDTO> updateIndustry(@RequestBody IndustryDTO industryDTO) throws URISyntaxException {
        log.debug("REST request to update Industry : {}", industryDTO);
        if (industryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IndustryDTO result = industryService.save(industryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, industryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /industries} : get all the industries.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of industries in body.
     */
    @GetMapping("/industries")
    public List<IndustryDTO> getAllIndustries() {
        log.debug("REST request to get all Industries");
        return industryService.findAll();
    }

    /**
     * {@code GET  /industries/:id} : get the "id" industry.
     *
     * @param id the id of the industryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the industryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/industries/{id}")
    public ResponseEntity<IndustryDTO> getIndustry(@PathVariable Long id) {
        log.debug("REST request to get Industry : {}", id);
        Optional<IndustryDTO> industryDTO = industryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(industryDTO);
    }

    /**
     * {@code DELETE  /industries/:id} : delete the "id" industry.
     *
     * @param id the id of the industryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/industries/{id}")
    public ResponseEntity<Void> deleteIndustry(@PathVariable Long id) {
        log.debug("REST request to delete Industry : {}", id);
        industryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
