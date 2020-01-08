package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.Supplies;
import com.eshipper.repository.SuppliesRepository;
import com.eshipper.service.SuppliesService;
import com.eshipper.service.dto.SuppliesDTO;
import com.eshipper.service.mapper.SuppliesMapper;
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
 * Integration tests for the {@link SuppliesResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class SuppliesResourceIT {

    private static final String DEFAULT_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private SuppliesRepository suppliesRepository;

    @Autowired
    private SuppliesMapper suppliesMapper;

    @Autowired
    private SuppliesService suppliesService;

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

    private MockMvc restSuppliesMockMvc;

    private Supplies supplies;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SuppliesResource suppliesResource = new SuppliesResource(suppliesService);
        this.restSuppliesMockMvc = MockMvcBuilders.standaloneSetup(suppliesResource)
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
    public static Supplies createEntity(EntityManager em) {
        Supplies supplies = new Supplies()
            .item(DEFAULT_ITEM)
            .name(DEFAULT_NAME);
        return supplies;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Supplies createUpdatedEntity(EntityManager em) {
        Supplies supplies = new Supplies()
            .item(UPDATED_ITEM)
            .name(UPDATED_NAME);
        return supplies;
    }

    @BeforeEach
    public void initTest() {
        supplies = createEntity(em);
    }

    @Test
    @Transactional
    public void createSupplies() throws Exception {
        int databaseSizeBeforeCreate = suppliesRepository.findAll().size();

        // Create the Supplies
        SuppliesDTO suppliesDTO = suppliesMapper.toDto(supplies);
        restSuppliesMockMvc.perform(post("/api/supplies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suppliesDTO)))
            .andExpect(status().isCreated());

        // Validate the Supplies in the database
        List<Supplies> suppliesList = suppliesRepository.findAll();
        assertThat(suppliesList).hasSize(databaseSizeBeforeCreate + 1);
        Supplies testSupplies = suppliesList.get(suppliesList.size() - 1);
        assertThat(testSupplies.getItem()).isEqualTo(DEFAULT_ITEM);
        assertThat(testSupplies.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createSuppliesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = suppliesRepository.findAll().size();

        // Create the Supplies with an existing ID
        supplies.setId(1L);
        SuppliesDTO suppliesDTO = suppliesMapper.toDto(supplies);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSuppliesMockMvc.perform(post("/api/supplies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suppliesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Supplies in the database
        List<Supplies> suppliesList = suppliesRepository.findAll();
        assertThat(suppliesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSupplies() throws Exception {
        // Initialize the database
        suppliesRepository.saveAndFlush(supplies);

        // Get all the suppliesList
        restSuppliesMockMvc.perform(get("/api/supplies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(supplies.getId().intValue())))
            .andExpect(jsonPath("$.[*].item").value(hasItem(DEFAULT_ITEM)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getSupplies() throws Exception {
        // Initialize the database
        suppliesRepository.saveAndFlush(supplies);

        // Get the supplies
        restSuppliesMockMvc.perform(get("/api/supplies/{id}", supplies.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(supplies.getId().intValue()))
            .andExpect(jsonPath("$.item").value(DEFAULT_ITEM))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingSupplies() throws Exception {
        // Get the supplies
        restSuppliesMockMvc.perform(get("/api/supplies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSupplies() throws Exception {
        // Initialize the database
        suppliesRepository.saveAndFlush(supplies);

        int databaseSizeBeforeUpdate = suppliesRepository.findAll().size();

        // Update the supplies
        Supplies updatedSupplies = suppliesRepository.findById(supplies.getId()).get();
        // Disconnect from session so that the updates on updatedSupplies are not directly saved in db
        em.detach(updatedSupplies);
        updatedSupplies
            .item(UPDATED_ITEM)
            .name(UPDATED_NAME);
        SuppliesDTO suppliesDTO = suppliesMapper.toDto(updatedSupplies);

        restSuppliesMockMvc.perform(put("/api/supplies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suppliesDTO)))
            .andExpect(status().isOk());

        // Validate the Supplies in the database
        List<Supplies> suppliesList = suppliesRepository.findAll();
        assertThat(suppliesList).hasSize(databaseSizeBeforeUpdate);
        Supplies testSupplies = suppliesList.get(suppliesList.size() - 1);
        assertThat(testSupplies.getItem()).isEqualTo(UPDATED_ITEM);
        assertThat(testSupplies.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingSupplies() throws Exception {
        int databaseSizeBeforeUpdate = suppliesRepository.findAll().size();

        // Create the Supplies
        SuppliesDTO suppliesDTO = suppliesMapper.toDto(supplies);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSuppliesMockMvc.perform(put("/api/supplies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suppliesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Supplies in the database
        List<Supplies> suppliesList = suppliesRepository.findAll();
        assertThat(suppliesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSupplies() throws Exception {
        // Initialize the database
        suppliesRepository.saveAndFlush(supplies);

        int databaseSizeBeforeDelete = suppliesRepository.findAll().size();

        // Delete the supplies
        restSuppliesMockMvc.perform(delete("/api/supplies/{id}", supplies.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Supplies> suppliesList = suppliesRepository.findAll();
        assertThat(suppliesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
