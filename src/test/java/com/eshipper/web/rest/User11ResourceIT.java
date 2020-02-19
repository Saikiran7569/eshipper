package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.User11;
import com.eshipper.repository.User11Repository;
import com.eshipper.service.User11Service;
import com.eshipper.service.dto.User11DTO;
import com.eshipper.service.mapper.User11Mapper;
import com.eshipper.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link User11Resource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class User11ResourceIT {

    @Autowired
    private User11Repository user11Repository;

    @Autowired
    private User11Mapper user11Mapper;

    @Autowired
    private User11Service user11Service;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restUser11MockMvc;

    private User11 user11;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final User11Resource user11Resource = new User11Resource(user11Service);
        this.restUser11MockMvc = MockMvcBuilders.standaloneSetup(user11Resource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static User11 createEntity(EntityManager em) {
        User11 user11 = new User11();
        return user11;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static User11 createUpdatedEntity(EntityManager em) {
        User11 user11 = new User11();
        return user11;
    }

    @BeforeEach
    public void initTest() {
        user11 = createEntity(em);
    }

    @Test
    @Transactional
    public void createUser11() throws Exception {
        int databaseSizeBeforeCreate = user11Repository.findAll().size();

        // Create the User11
        User11DTO user11DTO = user11Mapper.toDto(user11);
        restUser11MockMvc.perform(post("/api/user-11-s")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(user11DTO)))
            .andExpect(status().isCreated());

        // Validate the User11 in the database
        List<User11> user11List = user11Repository.findAll();
        assertThat(user11List).hasSize(databaseSizeBeforeCreate + 1);
        User11 testUser11 = user11List.get(user11List.size() - 1);
    }

    @Test
    @Transactional
    public void createUser11WithExistingId() throws Exception {
        int databaseSizeBeforeCreate = user11Repository.findAll().size();

        // Create the User11 with an existing ID
        user11.setId(1L);
        User11DTO user11DTO = user11Mapper.toDto(user11);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUser11MockMvc.perform(post("/api/user-11-s")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(user11DTO)))
            .andExpect(status().isBadRequest());

        // Validate the User11 in the database
        List<User11> user11List = user11Repository.findAll();
        assertThat(user11List).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUser11S() throws Exception {
        // Initialize the database
        user11Repository.saveAndFlush(user11);

        // Get all the user11List
        restUser11MockMvc.perform(get("/api/user-11-s?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(user11.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getUser11() throws Exception {
        // Initialize the database
        user11Repository.saveAndFlush(user11);

        // Get the user11
        restUser11MockMvc.perform(get("/api/user-11-s/{id}", user11.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(user11.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUser11() throws Exception {
        // Get the user11
        restUser11MockMvc.perform(get("/api/user-11-s/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUser11() throws Exception {
        // Initialize the database
        user11Repository.saveAndFlush(user11);

        int databaseSizeBeforeUpdate = user11Repository.findAll().size();

        // Update the user11
        User11 updatedUser11 = user11Repository.findById(user11.getId()).get();
        // Disconnect from session so that the updates on updatedUser11 are not directly saved in db
        em.detach(updatedUser11);
        User11DTO user11DTO = user11Mapper.toDto(updatedUser11);

        restUser11MockMvc.perform(put("/api/user-11-s")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(user11DTO)))
            .andExpect(status().isOk());

        // Validate the User11 in the database
        List<User11> user11List = user11Repository.findAll();
        assertThat(user11List).hasSize(databaseSizeBeforeUpdate);
        User11 testUser11 = user11List.get(user11List.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingUser11() throws Exception {
        int databaseSizeBeforeUpdate = user11Repository.findAll().size();

        // Create the User11
        User11DTO user11DTO = user11Mapper.toDto(user11);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUser11MockMvc.perform(put("/api/user-11-s")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(user11DTO)))
            .andExpect(status().isBadRequest());

        // Validate the User11 in the database
        List<User11> user11List = user11Repository.findAll();
        assertThat(user11List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUser11() throws Exception {
        // Initialize the database
        user11Repository.saveAndFlush(user11);

        int databaseSizeBeforeDelete = user11Repository.findAll().size();

        // Delete the user11
        restUser11MockMvc.perform(delete("/api/user-11-s/{id}", user11.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<User11> user11List = user11Repository.findAll();
        assertThat(user11List).hasSize(databaseSizeBeforeDelete - 1);
    }
}
