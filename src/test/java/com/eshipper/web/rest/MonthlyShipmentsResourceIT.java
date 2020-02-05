package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.MonthlyShipments;
import com.eshipper.repository.MonthlyShipmentsRepository;
import com.eshipper.service.MonthlyShipmentsService;
import com.eshipper.service.dto.MonthlyShipmentsDTO;
import com.eshipper.service.mapper.MonthlyShipmentsMapper;
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
 * Integration tests for the {@link MonthlyShipmentsResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class MonthlyShipmentsResourceIT {

    private static final String DEFAULT_RANGE = "AAAAAAAAAA";
    private static final String UPDATED_RANGE = "BBBBBBBBBB";

    @Autowired
    private MonthlyShipmentsRepository monthlyShipmentsRepository;

    @Autowired
    private MonthlyShipmentsMapper monthlyShipmentsMapper;

    @Autowired
    private MonthlyShipmentsService monthlyShipmentsService;

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

    private MockMvc restMonthlyShipmentsMockMvc;

    private MonthlyShipments monthlyShipments;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MonthlyShipmentsResource monthlyShipmentsResource = new MonthlyShipmentsResource(monthlyShipmentsService);
        this.restMonthlyShipmentsMockMvc = MockMvcBuilders.standaloneSetup(monthlyShipmentsResource)
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
    public static MonthlyShipments createEntity(EntityManager em) {
        MonthlyShipments monthlyShipments = new MonthlyShipments()
            .range(DEFAULT_RANGE);
        return monthlyShipments;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MonthlyShipments createUpdatedEntity(EntityManager em) {
        MonthlyShipments monthlyShipments = new MonthlyShipments()
            .range(UPDATED_RANGE);
        return monthlyShipments;
    }

    @BeforeEach
    public void initTest() {
        monthlyShipments = createEntity(em);
    }

    @Test
    @Transactional
    public void createMonthlyShipments() throws Exception {
        int databaseSizeBeforeCreate = monthlyShipmentsRepository.findAll().size();

        // Create the MonthlyShipments
        MonthlyShipmentsDTO monthlyShipmentsDTO = monthlyShipmentsMapper.toDto(monthlyShipments);
        restMonthlyShipmentsMockMvc.perform(post("/api/monthly-shipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(monthlyShipmentsDTO)))
            .andExpect(status().isCreated());

        // Validate the MonthlyShipments in the database
        List<MonthlyShipments> monthlyShipmentsList = monthlyShipmentsRepository.findAll();
        assertThat(monthlyShipmentsList).hasSize(databaseSizeBeforeCreate + 1);
        MonthlyShipments testMonthlyShipments = monthlyShipmentsList.get(monthlyShipmentsList.size() - 1);
        assertThat(testMonthlyShipments.getRange()).isEqualTo(DEFAULT_RANGE);
    }

    @Test
    @Transactional
    public void createMonthlyShipmentsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = monthlyShipmentsRepository.findAll().size();

        // Create the MonthlyShipments with an existing ID
        monthlyShipments.setId(1L);
        MonthlyShipmentsDTO monthlyShipmentsDTO = monthlyShipmentsMapper.toDto(monthlyShipments);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMonthlyShipmentsMockMvc.perform(post("/api/monthly-shipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(monthlyShipmentsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MonthlyShipments in the database
        List<MonthlyShipments> monthlyShipmentsList = monthlyShipmentsRepository.findAll();
        assertThat(monthlyShipmentsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMonthlyShipments() throws Exception {
        // Initialize the database
        monthlyShipmentsRepository.saveAndFlush(monthlyShipments);

        // Get all the monthlyShipmentsList
        restMonthlyShipmentsMockMvc.perform(get("/api/monthly-shipments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(monthlyShipments.getId().intValue())))
            .andExpect(jsonPath("$.[*].range").value(hasItem(DEFAULT_RANGE)));
    }
    
    @Test
    @Transactional
    public void getMonthlyShipments() throws Exception {
        // Initialize the database
        monthlyShipmentsRepository.saveAndFlush(monthlyShipments);

        // Get the monthlyShipments
        restMonthlyShipmentsMockMvc.perform(get("/api/monthly-shipments/{id}", monthlyShipments.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(monthlyShipments.getId().intValue()))
            .andExpect(jsonPath("$.range").value(DEFAULT_RANGE));
    }

    @Test
    @Transactional
    public void getNonExistingMonthlyShipments() throws Exception {
        // Get the monthlyShipments
        restMonthlyShipmentsMockMvc.perform(get("/api/monthly-shipments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMonthlyShipments() throws Exception {
        // Initialize the database
        monthlyShipmentsRepository.saveAndFlush(monthlyShipments);

        int databaseSizeBeforeUpdate = monthlyShipmentsRepository.findAll().size();

        // Update the monthlyShipments
        MonthlyShipments updatedMonthlyShipments = monthlyShipmentsRepository.findById(monthlyShipments.getId()).get();
        // Disconnect from session so that the updates on updatedMonthlyShipments are not directly saved in db
        em.detach(updatedMonthlyShipments);
        updatedMonthlyShipments
            .range(UPDATED_RANGE);
        MonthlyShipmentsDTO monthlyShipmentsDTO = monthlyShipmentsMapper.toDto(updatedMonthlyShipments);

        restMonthlyShipmentsMockMvc.perform(put("/api/monthly-shipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(monthlyShipmentsDTO)))
            .andExpect(status().isOk());

        // Validate the MonthlyShipments in the database
        List<MonthlyShipments> monthlyShipmentsList = monthlyShipmentsRepository.findAll();
        assertThat(monthlyShipmentsList).hasSize(databaseSizeBeforeUpdate);
        MonthlyShipments testMonthlyShipments = monthlyShipmentsList.get(monthlyShipmentsList.size() - 1);
        assertThat(testMonthlyShipments.getRange()).isEqualTo(UPDATED_RANGE);
    }

    @Test
    @Transactional
    public void updateNonExistingMonthlyShipments() throws Exception {
        int databaseSizeBeforeUpdate = monthlyShipmentsRepository.findAll().size();

        // Create the MonthlyShipments
        MonthlyShipmentsDTO monthlyShipmentsDTO = monthlyShipmentsMapper.toDto(monthlyShipments);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMonthlyShipmentsMockMvc.perform(put("/api/monthly-shipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(monthlyShipmentsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MonthlyShipments in the database
        List<MonthlyShipments> monthlyShipmentsList = monthlyShipmentsRepository.findAll();
        assertThat(monthlyShipmentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMonthlyShipments() throws Exception {
        // Initialize the database
        monthlyShipmentsRepository.saveAndFlush(monthlyShipments);

        int databaseSizeBeforeDelete = monthlyShipmentsRepository.findAll().size();

        // Delete the monthlyShipments
        restMonthlyShipmentsMockMvc.perform(delete("/api/monthly-shipments/{id}", monthlyShipments.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MonthlyShipments> monthlyShipmentsList = monthlyShipmentsRepository.findAll();
        assertThat(monthlyShipmentsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
