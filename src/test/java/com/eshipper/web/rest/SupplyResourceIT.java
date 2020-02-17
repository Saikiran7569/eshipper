package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.Supply;
import com.eshipper.repository.SupplyRepository;
import com.eshipper.service.SupplyService;
import com.eshipper.service.dto.SupplyDTO;
import com.eshipper.service.mapper.SupplyMapper;
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
 * Integration tests for the {@link SupplyResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class SupplyResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOGO_PATH = "AAAAAAAAAA";
    private static final String UPDATED_LOGO_PATH = "BBBBBBBBBB";

    @Autowired
    private SupplyRepository supplyRepository;

    @Autowired
    private SupplyMapper supplyMapper;

    @Autowired
    private SupplyService supplyService;

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

    private MockMvc restSupplyMockMvc;

    private Supply supply;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SupplyResource supplyResource = new SupplyResource(supplyService);
        this.restSupplyMockMvc = MockMvcBuilders.standaloneSetup(supplyResource)
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
    public static Supply createEntity(EntityManager em) {
        Supply supply = new Supply()
            .itemName(DEFAULT_ITEM_NAME)
            .logoPath(DEFAULT_LOGO_PATH);
        return supply;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Supply createUpdatedEntity(EntityManager em) {
        Supply supply = new Supply()
            .itemName(UPDATED_ITEM_NAME)
            .logoPath(UPDATED_LOGO_PATH);
        return supply;
    }

    @BeforeEach
    public void initTest() {
        supply = createEntity(em);
    }

    @Test
    @Transactional
    public void createSupply() throws Exception {
        int databaseSizeBeforeCreate = supplyRepository.findAll().size();

        // Create the Supply
        SupplyDTO supplyDTO = supplyMapper.toDto(supply);
        restSupplyMockMvc.perform(post("/api/supplies")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supplyDTO)))
            .andExpect(status().isCreated());

        // Validate the Supply in the database
        List<Supply> supplyList = supplyRepository.findAll();
        assertThat(supplyList).hasSize(databaseSizeBeforeCreate + 1);
        Supply testSupply = supplyList.get(supplyList.size() - 1);
        assertThat(testSupply.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testSupply.getLogoPath()).isEqualTo(DEFAULT_LOGO_PATH);
    }

    @Test
    @Transactional
    public void createSupplyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = supplyRepository.findAll().size();

        // Create the Supply with an existing ID
        supply.setId(1L);
        SupplyDTO supplyDTO = supplyMapper.toDto(supply);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSupplyMockMvc.perform(post("/api/supplies")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supplyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Supply in the database
        List<Supply> supplyList = supplyRepository.findAll();
        assertThat(supplyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSupplies() throws Exception {
        // Initialize the database
        supplyRepository.saveAndFlush(supply);

        // Get all the supplyList
        restSupplyMockMvc.perform(get("/api/supplies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(supply.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].logoPath").value(hasItem(DEFAULT_LOGO_PATH)));
    }
    
    @Test
    @Transactional
    public void getSupply() throws Exception {
        // Initialize the database
        supplyRepository.saveAndFlush(supply);

        // Get the supply
        restSupplyMockMvc.perform(get("/api/supplies/{id}", supply.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(supply.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.logoPath").value(DEFAULT_LOGO_PATH));
    }

    @Test
    @Transactional
    public void getNonExistingSupply() throws Exception {
        // Get the supply
        restSupplyMockMvc.perform(get("/api/supplies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSupply() throws Exception {
        // Initialize the database
        supplyRepository.saveAndFlush(supply);

        int databaseSizeBeforeUpdate = supplyRepository.findAll().size();

        // Update the supply
        Supply updatedSupply = supplyRepository.findById(supply.getId()).get();
        // Disconnect from session so that the updates on updatedSupply are not directly saved in db
        em.detach(updatedSupply);
        updatedSupply
            .itemName(UPDATED_ITEM_NAME)
            .logoPath(UPDATED_LOGO_PATH);
        SupplyDTO supplyDTO = supplyMapper.toDto(updatedSupply);

        restSupplyMockMvc.perform(put("/api/supplies")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supplyDTO)))
            .andExpect(status().isOk());

        // Validate the Supply in the database
        List<Supply> supplyList = supplyRepository.findAll();
        assertThat(supplyList).hasSize(databaseSizeBeforeUpdate);
        Supply testSupply = supplyList.get(supplyList.size() - 1);
        assertThat(testSupply.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testSupply.getLogoPath()).isEqualTo(UPDATED_LOGO_PATH);
    }

    @Test
    @Transactional
    public void updateNonExistingSupply() throws Exception {
        int databaseSizeBeforeUpdate = supplyRepository.findAll().size();

        // Create the Supply
        SupplyDTO supplyDTO = supplyMapper.toDto(supply);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSupplyMockMvc.perform(put("/api/supplies")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(supplyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Supply in the database
        List<Supply> supplyList = supplyRepository.findAll();
        assertThat(supplyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSupply() throws Exception {
        // Initialize the database
        supplyRepository.saveAndFlush(supply);

        int databaseSizeBeforeDelete = supplyRepository.findAll().size();

        // Delete the supply
        restSupplyMockMvc.perform(delete("/api/supplies/{id}", supply.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Supply> supplyList = supplyRepository.findAll();
        assertThat(supplyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
