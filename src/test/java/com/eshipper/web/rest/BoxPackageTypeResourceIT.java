package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.BoxPackageType;
import com.eshipper.repository.BoxPackageTypeRepository;
import com.eshipper.service.BoxPackageTypeService;
import com.eshipper.service.dto.BoxPackageTypeDTO;
import com.eshipper.service.mapper.BoxPackageTypeMapper;
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
 * Integration tests for the {@link BoxPackageTypeResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class BoxPackageTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private BoxPackageTypeRepository boxPackageTypeRepository;

    @Autowired
    private BoxPackageTypeMapper boxPackageTypeMapper;

    @Autowired
    private BoxPackageTypeService boxPackageTypeService;

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

    private MockMvc restBoxPackageTypeMockMvc;

    private BoxPackageType boxPackageType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BoxPackageTypeResource boxPackageTypeResource = new BoxPackageTypeResource(boxPackageTypeService);
        this.restBoxPackageTypeMockMvc = MockMvcBuilders.standaloneSetup(boxPackageTypeResource)
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
    public static BoxPackageType createEntity(EntityManager em) {
        BoxPackageType boxPackageType = new BoxPackageType()
            .name(DEFAULT_NAME);
        return boxPackageType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BoxPackageType createUpdatedEntity(EntityManager em) {
        BoxPackageType boxPackageType = new BoxPackageType()
            .name(UPDATED_NAME);
        return boxPackageType;
    }

    @BeforeEach
    public void initTest() {
        boxPackageType = createEntity(em);
    }

    @Test
    @Transactional
    public void createBoxPackageType() throws Exception {
        int databaseSizeBeforeCreate = boxPackageTypeRepository.findAll().size();

        // Create the BoxPackageType
        BoxPackageTypeDTO boxPackageTypeDTO = boxPackageTypeMapper.toDto(boxPackageType);
        restBoxPackageTypeMockMvc.perform(post("/api/box-package-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boxPackageTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the BoxPackageType in the database
        List<BoxPackageType> boxPackageTypeList = boxPackageTypeRepository.findAll();
        assertThat(boxPackageTypeList).hasSize(databaseSizeBeforeCreate + 1);
        BoxPackageType testBoxPackageType = boxPackageTypeList.get(boxPackageTypeList.size() - 1);
        assertThat(testBoxPackageType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createBoxPackageTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = boxPackageTypeRepository.findAll().size();

        // Create the BoxPackageType with an existing ID
        boxPackageType.setId(1L);
        BoxPackageTypeDTO boxPackageTypeDTO = boxPackageTypeMapper.toDto(boxPackageType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBoxPackageTypeMockMvc.perform(post("/api/box-package-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boxPackageTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BoxPackageType in the database
        List<BoxPackageType> boxPackageTypeList = boxPackageTypeRepository.findAll();
        assertThat(boxPackageTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBoxPackageTypes() throws Exception {
        // Initialize the database
        boxPackageTypeRepository.saveAndFlush(boxPackageType);

        // Get all the boxPackageTypeList
        restBoxPackageTypeMockMvc.perform(get("/api/box-package-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(boxPackageType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getBoxPackageType() throws Exception {
        // Initialize the database
        boxPackageTypeRepository.saveAndFlush(boxPackageType);

        // Get the boxPackageType
        restBoxPackageTypeMockMvc.perform(get("/api/box-package-types/{id}", boxPackageType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(boxPackageType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingBoxPackageType() throws Exception {
        // Get the boxPackageType
        restBoxPackageTypeMockMvc.perform(get("/api/box-package-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBoxPackageType() throws Exception {
        // Initialize the database
        boxPackageTypeRepository.saveAndFlush(boxPackageType);

        int databaseSizeBeforeUpdate = boxPackageTypeRepository.findAll().size();

        // Update the boxPackageType
        BoxPackageType updatedBoxPackageType = boxPackageTypeRepository.findById(boxPackageType.getId()).get();
        // Disconnect from session so that the updates on updatedBoxPackageType are not directly saved in db
        em.detach(updatedBoxPackageType);
        updatedBoxPackageType
            .name(UPDATED_NAME);
        BoxPackageTypeDTO boxPackageTypeDTO = boxPackageTypeMapper.toDto(updatedBoxPackageType);

        restBoxPackageTypeMockMvc.perform(put("/api/box-package-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boxPackageTypeDTO)))
            .andExpect(status().isOk());

        // Validate the BoxPackageType in the database
        List<BoxPackageType> boxPackageTypeList = boxPackageTypeRepository.findAll();
        assertThat(boxPackageTypeList).hasSize(databaseSizeBeforeUpdate);
        BoxPackageType testBoxPackageType = boxPackageTypeList.get(boxPackageTypeList.size() - 1);
        assertThat(testBoxPackageType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingBoxPackageType() throws Exception {
        int databaseSizeBeforeUpdate = boxPackageTypeRepository.findAll().size();

        // Create the BoxPackageType
        BoxPackageTypeDTO boxPackageTypeDTO = boxPackageTypeMapper.toDto(boxPackageType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBoxPackageTypeMockMvc.perform(put("/api/box-package-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boxPackageTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BoxPackageType in the database
        List<BoxPackageType> boxPackageTypeList = boxPackageTypeRepository.findAll();
        assertThat(boxPackageTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBoxPackageType() throws Exception {
        // Initialize the database
        boxPackageTypeRepository.saveAndFlush(boxPackageType);

        int databaseSizeBeforeDelete = boxPackageTypeRepository.findAll().size();

        // Delete the boxPackageType
        restBoxPackageTypeMockMvc.perform(delete("/api/box-package-types/{id}", boxPackageType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BoxPackageType> boxPackageTypeList = boxPackageTypeRepository.findAll();
        assertThat(boxPackageTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
