package com.eshipper.web.rest;

import com.eshipper.service.User11Service;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.User11DTO;

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
 * REST controller for managing {@link com.eshipper.domain.User11}.
 */
@RestController
@RequestMapping("/api")
public class User11Resource {

    private final Logger log = LoggerFactory.getLogger(User11Resource.class);

    private static final String ENTITY_NAME = "user11";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final User11Service user11Service;

    public User11Resource(User11Service user11Service) {
        this.user11Service = user11Service;
    }

    /**
     * {@code POST  /user-11-s} : Create a new user11.
     *
     * @param user11DTO the user11DTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new user11DTO, or with status {@code 400 (Bad Request)} if the user11 has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-11-s")
    public ResponseEntity<User11DTO> createUser11(@RequestBody User11DTO user11DTO) throws URISyntaxException {
        log.debug("REST request to save User11 : {}", user11DTO);
        if (user11DTO.getId() != null) {
            throw new BadRequestAlertException("A new user11 cannot already have an ID", ENTITY_NAME, "idexists");
        }
        User11DTO result = user11Service.save(user11DTO);
        return ResponseEntity.created(new URI("/api/user-11-s/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-11-s} : Updates an existing user11.
     *
     * @param user11DTO the user11DTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated user11DTO,
     * or with status {@code 400 (Bad Request)} if the user11DTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the user11DTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-11-s")
    public ResponseEntity<User11DTO> updateUser11(@RequestBody User11DTO user11DTO) throws URISyntaxException {
        log.debug("REST request to update User11 : {}", user11DTO);
        if (user11DTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        User11DTO result = user11Service.save(user11DTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, user11DTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-11-s} : get all the user11S.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of user11S in body.
     */
    @GetMapping("/user-11-s")
    public List<User11DTO> getAllUser11S() {
        log.debug("REST request to get all User11S");
        return user11Service.findAll();
    }

    /**
     * {@code GET  /user-11-s/:id} : get the "id" user11.
     *
     * @param id the id of the user11DTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the user11DTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-11-s/{id}")
    public ResponseEntity<User11DTO> getUser11(@PathVariable Long id) {
        log.debug("REST request to get User11 : {}", id);
        Optional<User11DTO> user11DTO = user11Service.findOne(id);
        return ResponseUtil.wrapOrNotFound(user11DTO);
    }

    /**
     * {@code DELETE  /user-11-s/:id} : delete the "id" user11.
     *
     * @param id the id of the user11DTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-11-s/{id}")
    public ResponseEntity<Void> deleteUser11(@PathVariable Long id) {
        log.debug("REST request to delete User11 : {}", id);
        user11Service.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
