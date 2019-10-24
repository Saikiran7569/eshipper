package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.PalletType;
import com.eshipper.repository.PalletTypeRepository;
import com.eshipper.service.PalletTypeService;
import com.eshipper.service.dto.PalletTypeDTO;
import com.eshipper.service.mapper.PalletTypeMapper;
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
 * Integration tests for the {@link PalletTypeResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class PalletTypeResourceIT {

    @Autowired
    private PalletTypeRepository palletTypeRepository;

    @Autowired
    private PalletTypeMapper palletTypeMapper;

    @Autowired
    private PalletTypeService palletTypeService;

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

    private MockMvc restPalletTypeMockMvc;

    private PalletType palletType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PalletTypeResource palletTypeResource = new PalletTypeResource(palletTypeService);
        this.restPalletTypeMockMvc = MockMvcBuilders.standaloneSetup(palletTypeResource)
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
    public static PalletType createEntity(EntityManager em) {
        PalletType palletType = new PalletType();
        return palletType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PalletType createUpdatedEntity(EntityManager em) {
        PalletType palletType = new PalletType();
        return palletType;
    }

    @BeforeEach
    public void initTest() {
        palletType = createEntity(em);
    }

    @Test
    @Transactional
    public void createPalletType() throws Exception {
        int databaseSizeBeforeCreate = palletTypeRepository.findAll().size();

        // Create the PalletType
        PalletTypeDTO palletTypeDTO = palletTypeMapper.toDto(palletType);
        restPalletTypeMockMvc.perform(post("/api/pallet-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(palletTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the PalletType in the database
        List<PalletType> palletTypeList = palletTypeRepository.findAll();
        assertThat(palletTypeList).hasSize(databaseSizeBeforeCreate + 1);
        PalletType testPalletType = palletTypeList.get(palletTypeList.size() - 1);
    }

    @Test
    @Transactional
    public void createPalletTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = palletTypeRepository.findAll().size();

        // Create the PalletType with an existing ID
        palletType.setId(1L);
        PalletTypeDTO palletTypeDTO = palletTypeMapper.toDto(palletType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPalletTypeMockMvc.perform(post("/api/pallet-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(palletTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PalletType in the database
        List<PalletType> palletTypeList = palletTypeRepository.findAll();
        assertThat(palletTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPalletTypes() throws Exception {
        // Initialize the database
        palletTypeRepository.saveAndFlush(palletType);

        // Get all the palletTypeList
        restPalletTypeMockMvc.perform(get("/api/pallet-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(palletType.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getPalletType() throws Exception {
        // Initialize the database
        palletTypeRepository.saveAndFlush(palletType);

        // Get the palletType
        restPalletTypeMockMvc.perform(get("/api/pallet-types/{id}", palletType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(palletType.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPalletType() throws Exception {
        // Get the palletType
        restPalletTypeMockMvc.perform(get("/api/pallet-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePalletType() throws Exception {
        // Initialize the database
        palletTypeRepository.saveAndFlush(palletType);

        int databaseSizeBeforeUpdate = palletTypeRepository.findAll().size();

        // Update the palletType
        PalletType updatedPalletType = palletTypeRepository.findById(palletType.getId()).get();
        // Disconnect from session so that the updates on updatedPalletType are not directly saved in db
        em.detach(updatedPalletType);
        PalletTypeDTO palletTypeDTO = palletTypeMapper.toDto(updatedPalletType);

        restPalletTypeMockMvc.perform(put("/api/pallet-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(palletTypeDTO)))
            .andExpect(status().isOk());

        // Validate the PalletType in the database
        List<PalletType> palletTypeList = palletTypeRepository.findAll();
        assertThat(palletTypeList).hasSize(databaseSizeBeforeUpdate);
        PalletType testPalletType = palletTypeList.get(palletTypeList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingPalletType() throws Exception {
        int databaseSizeBeforeUpdate = palletTypeRepository.findAll().size();

        // Create the PalletType
        PalletTypeDTO palletTypeDTO = palletTypeMapper.toDto(palletType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPalletTypeMockMvc.perform(put("/api/pallet-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(palletTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PalletType in the database
        List<PalletType> palletTypeList = palletTypeRepository.findAll();
        assertThat(palletTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePalletType() throws Exception {
        // Initialize the database
        palletTypeRepository.saveAndFlush(palletType);

        int databaseSizeBeforeDelete = palletTypeRepository.findAll().size();

        // Delete the palletType
        restPalletTypeMockMvc.perform(delete("/api/pallet-types/{id}", palletType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PalletType> palletTypeList = palletTypeRepository.findAll();
        assertThat(palletTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PalletType.class);
        PalletType palletType1 = new PalletType();
        palletType1.setId(1L);
        PalletType palletType2 = new PalletType();
        palletType2.setId(palletType1.getId());
        assertThat(palletType1).isEqualTo(palletType2);
        palletType2.setId(2L);
        assertThat(palletType1).isNotEqualTo(palletType2);
        palletType1.setId(null);
        assertThat(palletType1).isNotEqualTo(palletType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PalletTypeDTO.class);
        PalletTypeDTO palletTypeDTO1 = new PalletTypeDTO();
        palletTypeDTO1.setId(1L);
        PalletTypeDTO palletTypeDTO2 = new PalletTypeDTO();
        assertThat(palletTypeDTO1).isNotEqualTo(palletTypeDTO2);
        palletTypeDTO2.setId(palletTypeDTO1.getId());
        assertThat(palletTypeDTO1).isEqualTo(palletTypeDTO2);
        palletTypeDTO2.setId(2L);
        assertThat(palletTypeDTO1).isNotEqualTo(palletTypeDTO2);
        palletTypeDTO1.setId(null);
        assertThat(palletTypeDTO1).isNotEqualTo(palletTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(palletTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(palletTypeMapper.fromId(null)).isNull();
    }
}
