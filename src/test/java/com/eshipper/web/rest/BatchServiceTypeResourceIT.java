package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.BatchServiceType;
import com.eshipper.repository.BatchServiceTypeRepository;
import com.eshipper.service.BatchServiceTypeService;
import com.eshipper.service.dto.BatchServiceTypeDTO;
import com.eshipper.service.mapper.BatchServiceTypeMapper;
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
 * Integration tests for the {@link BatchServiceTypeResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class BatchServiceTypeResourceIT {

    private static final String DEFAULT_SERVICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private BatchServiceTypeRepository batchServiceTypeRepository;

    @Autowired
    private BatchServiceTypeMapper batchServiceTypeMapper;

    @Autowired
    private BatchServiceTypeService batchServiceTypeService;

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

    private MockMvc restBatchServiceTypeMockMvc;

    private BatchServiceType batchServiceType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BatchServiceTypeResource batchServiceTypeResource = new BatchServiceTypeResource(batchServiceTypeService);
        this.restBatchServiceTypeMockMvc = MockMvcBuilders.standaloneSetup(batchServiceTypeResource)
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
    public static BatchServiceType createEntity(EntityManager em) {
        BatchServiceType batchServiceType = new BatchServiceType()
            .serviceName(DEFAULT_SERVICE_NAME)
            .value(DEFAULT_VALUE);
        return batchServiceType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BatchServiceType createUpdatedEntity(EntityManager em) {
        BatchServiceType batchServiceType = new BatchServiceType()
            .serviceName(UPDATED_SERVICE_NAME)
            .value(UPDATED_VALUE);
        return batchServiceType;
    }

    @BeforeEach
    public void initTest() {
        batchServiceType = createEntity(em);
    }

    @Test
    @Transactional
    public void createBatchServiceType() throws Exception {
        int databaseSizeBeforeCreate = batchServiceTypeRepository.findAll().size();

        // Create the BatchServiceType
        BatchServiceTypeDTO batchServiceTypeDTO = batchServiceTypeMapper.toDto(batchServiceType);
        restBatchServiceTypeMockMvc.perform(post("/api/batch-service-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchServiceTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the BatchServiceType in the database
        List<BatchServiceType> batchServiceTypeList = batchServiceTypeRepository.findAll();
        assertThat(batchServiceTypeList).hasSize(databaseSizeBeforeCreate + 1);
        BatchServiceType testBatchServiceType = batchServiceTypeList.get(batchServiceTypeList.size() - 1);
        assertThat(testBatchServiceType.getServiceName()).isEqualTo(DEFAULT_SERVICE_NAME);
        assertThat(testBatchServiceType.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createBatchServiceTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = batchServiceTypeRepository.findAll().size();

        // Create the BatchServiceType with an existing ID
        batchServiceType.setId(1L);
        BatchServiceTypeDTO batchServiceTypeDTO = batchServiceTypeMapper.toDto(batchServiceType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBatchServiceTypeMockMvc.perform(post("/api/batch-service-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchServiceTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BatchServiceType in the database
        List<BatchServiceType> batchServiceTypeList = batchServiceTypeRepository.findAll();
        assertThat(batchServiceTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBatchServiceTypes() throws Exception {
        // Initialize the database
        batchServiceTypeRepository.saveAndFlush(batchServiceType);

        // Get all the batchServiceTypeList
        restBatchServiceTypeMockMvc.perform(get("/api/batch-service-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(batchServiceType.getId().intValue())))
            .andExpect(jsonPath("$.[*].serviceName").value(hasItem(DEFAULT_SERVICE_NAME)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }
    
    @Test
    @Transactional
    public void getBatchServiceType() throws Exception {
        // Initialize the database
        batchServiceTypeRepository.saveAndFlush(batchServiceType);

        // Get the batchServiceType
        restBatchServiceTypeMockMvc.perform(get("/api/batch-service-types/{id}", batchServiceType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(batchServiceType.getId().intValue()))
            .andExpect(jsonPath("$.serviceName").value(DEFAULT_SERVICE_NAME))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }

    @Test
    @Transactional
    public void getNonExistingBatchServiceType() throws Exception {
        // Get the batchServiceType
        restBatchServiceTypeMockMvc.perform(get("/api/batch-service-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBatchServiceType() throws Exception {
        // Initialize the database
        batchServiceTypeRepository.saveAndFlush(batchServiceType);

        int databaseSizeBeforeUpdate = batchServiceTypeRepository.findAll().size();

        // Update the batchServiceType
        BatchServiceType updatedBatchServiceType = batchServiceTypeRepository.findById(batchServiceType.getId()).get();
        // Disconnect from session so that the updates on updatedBatchServiceType are not directly saved in db
        em.detach(updatedBatchServiceType);
        updatedBatchServiceType
            .serviceName(UPDATED_SERVICE_NAME)
            .value(UPDATED_VALUE);
        BatchServiceTypeDTO batchServiceTypeDTO = batchServiceTypeMapper.toDto(updatedBatchServiceType);

        restBatchServiceTypeMockMvc.perform(put("/api/batch-service-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchServiceTypeDTO)))
            .andExpect(status().isOk());

        // Validate the BatchServiceType in the database
        List<BatchServiceType> batchServiceTypeList = batchServiceTypeRepository.findAll();
        assertThat(batchServiceTypeList).hasSize(databaseSizeBeforeUpdate);
        BatchServiceType testBatchServiceType = batchServiceTypeList.get(batchServiceTypeList.size() - 1);
        assertThat(testBatchServiceType.getServiceName()).isEqualTo(UPDATED_SERVICE_NAME);
        assertThat(testBatchServiceType.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingBatchServiceType() throws Exception {
        int databaseSizeBeforeUpdate = batchServiceTypeRepository.findAll().size();

        // Create the BatchServiceType
        BatchServiceTypeDTO batchServiceTypeDTO = batchServiceTypeMapper.toDto(batchServiceType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBatchServiceTypeMockMvc.perform(put("/api/batch-service-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(batchServiceTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BatchServiceType in the database
        List<BatchServiceType> batchServiceTypeList = batchServiceTypeRepository.findAll();
        assertThat(batchServiceTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBatchServiceType() throws Exception {
        // Initialize the database
        batchServiceTypeRepository.saveAndFlush(batchServiceType);

        int databaseSizeBeforeDelete = batchServiceTypeRepository.findAll().size();

        // Delete the batchServiceType
        restBatchServiceTypeMockMvc.perform(delete("/api/batch-service-types/{id}", batchServiceType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BatchServiceType> batchServiceTypeList = batchServiceTypeRepository.findAll();
        assertThat(batchServiceTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
