package com.eshipper.web.rest;

import com.eshipper.service.BoxPackageTypeService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.BoxPackageTypeDTO;

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
 * REST controller for managing {@link com.eshipper.domain.BoxPackageType}.
 */
@RestController
@RequestMapping("/api")
public class BoxPackageTypeResource {

    private final Logger log = LoggerFactory.getLogger(BoxPackageTypeResource.class);

    private static final String ENTITY_NAME = "boxPackageType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BoxPackageTypeService boxPackageTypeService;

    public BoxPackageTypeResource(BoxPackageTypeService boxPackageTypeService) {
        this.boxPackageTypeService = boxPackageTypeService;
    }

    /**
     * {@code POST  /box-package-types} : Create a new boxPackageType.
     *
     * @param boxPackageTypeDTO the boxPackageTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new boxPackageTypeDTO, or with status {@code 400 (Bad Request)} if the boxPackageType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/box-package-types")
    public ResponseEntity<BoxPackageTypeDTO> createBoxPackageType(@RequestBody BoxPackageTypeDTO boxPackageTypeDTO) throws URISyntaxException {
        log.debug("REST request to save BoxPackageType : {}", boxPackageTypeDTO);
        if (boxPackageTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new boxPackageType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BoxPackageTypeDTO result = boxPackageTypeService.save(boxPackageTypeDTO);
        return ResponseEntity.created(new URI("/api/box-package-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /box-package-types} : Updates an existing boxPackageType.
     *
     * @param boxPackageTypeDTO the boxPackageTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated boxPackageTypeDTO,
     * or with status {@code 400 (Bad Request)} if the boxPackageTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the boxPackageTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/box-package-types")
    public ResponseEntity<BoxPackageTypeDTO> updateBoxPackageType(@RequestBody BoxPackageTypeDTO boxPackageTypeDTO) throws URISyntaxException {
        log.debug("REST request to update BoxPackageType : {}", boxPackageTypeDTO);
        if (boxPackageTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BoxPackageTypeDTO result = boxPackageTypeService.save(boxPackageTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, boxPackageTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /box-package-types} : get all the boxPackageTypes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of boxPackageTypes in body.
     */
    @GetMapping("/box-package-types")
    public ResponseEntity<List<BoxPackageTypeDTO>> getAllBoxPackageTypes(Pageable pageable) {
        log.debug("REST request to get a page of BoxPackageTypes");
        Page<BoxPackageTypeDTO> page = boxPackageTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /box-package-types/:id} : get the "id" boxPackageType.
     *
     * @param id the id of the boxPackageTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the boxPackageTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/box-package-types/{id}")
    public ResponseEntity<BoxPackageTypeDTO> getBoxPackageType(@PathVariable Long id) {
        log.debug("REST request to get BoxPackageType : {}", id);
        Optional<BoxPackageTypeDTO> boxPackageTypeDTO = boxPackageTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(boxPackageTypeDTO);
    }

    /**
     * {@code DELETE  /box-package-types/:id} : delete the "id" boxPackageType.
     *
     * @param id the id of the boxPackageTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/box-package-types/{id}")
    public ResponseEntity<Void> deleteBoxPackageType(@PathVariable Long id) {
        log.debug("REST request to delete BoxPackageType : {}", id);
        boxPackageTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
