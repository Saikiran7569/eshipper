package com.eshipper.web.rest;

import com.eshipper.service.CommodityTypeService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.CommodityTypeDTO;

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
 * REST controller for managing {@link com.eshipper.domain.CommodityType}.
 */
@RestController
@RequestMapping("/api")
public class CommodityTypeResource {

    private final Logger log = LoggerFactory.getLogger(CommodityTypeResource.class);

    private static final String ENTITY_NAME = "commodityType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommodityTypeService commodityTypeService;

    public CommodityTypeResource(CommodityTypeService commodityTypeService) {
        this.commodityTypeService = commodityTypeService;
    }

    /**
     * {@code POST  /commodity-types} : Create a new commodityType.
     *
     * @param commodityTypeDTO the commodityTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commodityTypeDTO, or with status {@code 400 (Bad Request)} if the commodityType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/commodity-types")
    public ResponseEntity<CommodityTypeDTO> createCommodityType(@RequestBody CommodityTypeDTO commodityTypeDTO) throws URISyntaxException {
        log.debug("REST request to save CommodityType : {}", commodityTypeDTO);
        if (commodityTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new commodityType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommodityTypeDTO result = commodityTypeService.save(commodityTypeDTO);
        return ResponseEntity.created(new URI("/api/commodity-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /commodity-types} : Updates an existing commodityType.
     *
     * @param commodityTypeDTO the commodityTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commodityTypeDTO,
     * or with status {@code 400 (Bad Request)} if the commodityTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commodityTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/commodity-types")
    public ResponseEntity<CommodityTypeDTO> updateCommodityType(@RequestBody CommodityTypeDTO commodityTypeDTO) throws URISyntaxException {
        log.debug("REST request to update CommodityType : {}", commodityTypeDTO);
        if (commodityTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommodityTypeDTO result = commodityTypeService.save(commodityTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commodityTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /commodity-types} : get all the commodityTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commodityTypes in body.
     */
    @GetMapping("/commodity-types")
    public ResponseEntity<List<CommodityTypeDTO>> getAllCommodityTypes(Pageable pageable) {
        log.debug("REST request to get a page of CommodityTypes");
        Page<CommodityTypeDTO> page = commodityTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /commodity-types/:id} : get the "id" commodityType.
     *
     * @param id the id of the commodityTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commodityTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/commodity-types/{id}")
    public ResponseEntity<CommodityTypeDTO> getCommodityType(@PathVariable Long id) {
        log.debug("REST request to get CommodityType : {}", id);
        Optional<CommodityTypeDTO> commodityTypeDTO = commodityTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commodityTypeDTO);
    }

    /**
     * {@code DELETE  /commodity-types/:id} : delete the "id" commodityType.
     *
     * @param id the id of the commodityTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/commodity-types/{id}")
    public ResponseEntity<Void> deleteCommodityType(@PathVariable Long id) {
        log.debug("REST request to delete CommodityType : {}", id);

        commodityTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
