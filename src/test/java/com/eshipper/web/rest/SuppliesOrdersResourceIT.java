package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.SuppliesOrders;
import com.eshipper.repository.SuppliesOrdersRepository;
import com.eshipper.service.SuppliesOrdersService;
import com.eshipper.service.dto.SuppliesOrdersDTO;
import com.eshipper.service.mapper.SuppliesOrdersMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SuppliesOrdersResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class SuppliesOrdersResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SuppliesOrdersRepository suppliesOrdersRepository;

    @Autowired
    private SuppliesOrdersMapper suppliesOrdersMapper;

    @Autowired
    private SuppliesOrdersService suppliesOrdersService;

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

    private MockMvc restSuppliesOrdersMockMvc;

    private SuppliesOrders suppliesOrders;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SuppliesOrdersResource suppliesOrdersResource = new SuppliesOrdersResource(suppliesOrdersService);
        this.restSuppliesOrdersMockMvc = MockMvcBuilders.standaloneSetup(suppliesOrdersResource)
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
    public static SuppliesOrders createEntity(EntityManager em) {
        SuppliesOrders suppliesOrders = new SuppliesOrders()
            .itemName(DEFAULT_ITEM_NAME)
            .quantity(DEFAULT_QUANTITY)
            .createdDate(DEFAULT_CREATED_DATE);
        return suppliesOrders;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SuppliesOrders createUpdatedEntity(EntityManager em) {
        SuppliesOrders suppliesOrders = new SuppliesOrders()
            .itemName(UPDATED_ITEM_NAME)
            .quantity(UPDATED_QUANTITY)
            .createdDate(UPDATED_CREATED_DATE);
        return suppliesOrders;
    }

    @BeforeEach
    public void initTest() {
        suppliesOrders = createEntity(em);
    }

    @Test
    @Transactional
    public void createSuppliesOrders() throws Exception {
        int databaseSizeBeforeCreate = suppliesOrdersRepository.findAll().size();

        // Create the SuppliesOrders
        SuppliesOrdersDTO suppliesOrdersDTO = suppliesOrdersMapper.toDto(suppliesOrders);
        restSuppliesOrdersMockMvc.perform(post("/api/supplies-orders")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(suppliesOrdersDTO)))
            .andExpect(status().isCreated());

        // Validate the SuppliesOrders in the database
        List<SuppliesOrders> suppliesOrdersList = suppliesOrdersRepository.findAll();
        assertThat(suppliesOrdersList).hasSize(databaseSizeBeforeCreate + 1);
        SuppliesOrders testSuppliesOrders = suppliesOrdersList.get(suppliesOrdersList.size() - 1);
        assertThat(testSuppliesOrders.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testSuppliesOrders.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testSuppliesOrders.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    }

    @Test
    @Transactional
    public void createSuppliesOrdersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = suppliesOrdersRepository.findAll().size();

        // Create the SuppliesOrders with an existing ID
        suppliesOrders.setId(1L);
        SuppliesOrdersDTO suppliesOrdersDTO = suppliesOrdersMapper.toDto(suppliesOrders);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSuppliesOrdersMockMvc.perform(post("/api/supplies-orders")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(suppliesOrdersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SuppliesOrders in the database
        List<SuppliesOrders> suppliesOrdersList = suppliesOrdersRepository.findAll();
        assertThat(suppliesOrdersList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSuppliesOrders() throws Exception {
        // Initialize the database
        suppliesOrdersRepository.saveAndFlush(suppliesOrders);

        // Get all the suppliesOrdersList
        restSuppliesOrdersMockMvc.perform(get("/api/supplies-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(suppliesOrders.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getSuppliesOrders() throws Exception {
        // Initialize the database
        suppliesOrdersRepository.saveAndFlush(suppliesOrders);

        // Get the suppliesOrders
        restSuppliesOrdersMockMvc.perform(get("/api/supplies-orders/{id}", suppliesOrders.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(suppliesOrders.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSuppliesOrders() throws Exception {
        // Get the suppliesOrders
        restSuppliesOrdersMockMvc.perform(get("/api/supplies-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSuppliesOrders() throws Exception {
        // Initialize the database
        suppliesOrdersRepository.saveAndFlush(suppliesOrders);

        int databaseSizeBeforeUpdate = suppliesOrdersRepository.findAll().size();

        // Update the suppliesOrders
        SuppliesOrders updatedSuppliesOrders = suppliesOrdersRepository.findById(suppliesOrders.getId()).get();
        // Disconnect from session so that the updates on updatedSuppliesOrders are not directly saved in db
        em.detach(updatedSuppliesOrders);
        updatedSuppliesOrders
            .itemName(UPDATED_ITEM_NAME)
            .quantity(UPDATED_QUANTITY)
            .createdDate(UPDATED_CREATED_DATE);
        SuppliesOrdersDTO suppliesOrdersDTO = suppliesOrdersMapper.toDto(updatedSuppliesOrders);

        restSuppliesOrdersMockMvc.perform(put("/api/supplies-orders")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(suppliesOrdersDTO)))
            .andExpect(status().isOk());

        // Validate the SuppliesOrders in the database
        List<SuppliesOrders> suppliesOrdersList = suppliesOrdersRepository.findAll();
        assertThat(suppliesOrdersList).hasSize(databaseSizeBeforeUpdate);
        SuppliesOrders testSuppliesOrders = suppliesOrdersList.get(suppliesOrdersList.size() - 1);
        assertThat(testSuppliesOrders.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testSuppliesOrders.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testSuppliesOrders.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingSuppliesOrders() throws Exception {
        int databaseSizeBeforeUpdate = suppliesOrdersRepository.findAll().size();

        // Create the SuppliesOrders
        SuppliesOrdersDTO suppliesOrdersDTO = suppliesOrdersMapper.toDto(suppliesOrders);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSuppliesOrdersMockMvc.perform(put("/api/supplies-orders")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(suppliesOrdersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SuppliesOrders in the database
        List<SuppliesOrders> suppliesOrdersList = suppliesOrdersRepository.findAll();
        assertThat(suppliesOrdersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSuppliesOrders() throws Exception {
        // Initialize the database
        suppliesOrdersRepository.saveAndFlush(suppliesOrders);

        int databaseSizeBeforeDelete = suppliesOrdersRepository.findAll().size();

        // Delete the suppliesOrders
        restSuppliesOrdersMockMvc.perform(delete("/api/supplies-orders/{id}", suppliesOrders.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SuppliesOrders> suppliesOrdersList = suppliesOrdersRepository.findAll();
        assertThat(suppliesOrdersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
