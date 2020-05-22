package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.CommodityType;
import com.eshipper.repository.CommodityTypeRepository;
import com.eshipper.service.CommodityTypeService;
import com.eshipper.service.dto.CommodityTypeDTO;
import com.eshipper.service.mapper.CommodityTypeMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CommodityTypeResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommodityTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private CommodityTypeRepository commodityTypeRepository;

    @Autowired
    private CommodityTypeMapper commodityTypeMapper;

    @Autowired
    private CommodityTypeService commodityTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommodityTypeMockMvc;

    private CommodityType commodityType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommodityType createEntity(EntityManager em) {
        CommodityType commodityType = new CommodityType()
            .name(DEFAULT_NAME);
        return commodityType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommodityType createUpdatedEntity(EntityManager em) {
        CommodityType commodityType = new CommodityType()
            .name(UPDATED_NAME);
        return commodityType;
    }

    @BeforeEach
    public void initTest() {
        commodityType = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommodityType() throws Exception {
        int databaseSizeBeforeCreate = commodityTypeRepository.findAll().size();
        // Create the CommodityType
        CommodityTypeDTO commodityTypeDTO = commodityTypeMapper.toDto(commodityType);
        restCommodityTypeMockMvc.perform(post("/api/commodity-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commodityTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the CommodityType in the database
        List<CommodityType> commodityTypeList = commodityTypeRepository.findAll();
        assertThat(commodityTypeList).hasSize(databaseSizeBeforeCreate + 1);
        CommodityType testCommodityType = commodityTypeList.get(commodityTypeList.size() - 1);
        assertThat(testCommodityType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createCommodityTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commodityTypeRepository.findAll().size();

        // Create the CommodityType with an existing ID
        commodityType.setId(1L);
        CommodityTypeDTO commodityTypeDTO = commodityTypeMapper.toDto(commodityType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommodityTypeMockMvc.perform(post("/api/commodity-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commodityTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommodityType in the database
        List<CommodityType> commodityTypeList = commodityTypeRepository.findAll();
        assertThat(commodityTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCommodityTypes() throws Exception {
        // Initialize the database
        commodityTypeRepository.saveAndFlush(commodityType);

        // Get all the commodityTypeList
        restCommodityTypeMockMvc.perform(get("/api/commodity-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commodityType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getCommodityType() throws Exception {
        // Initialize the database
        commodityTypeRepository.saveAndFlush(commodityType);

        // Get the commodityType
        restCommodityTypeMockMvc.perform(get("/api/commodity-types/{id}", commodityType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commodityType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingCommodityType() throws Exception {
        // Get the commodityType
        restCommodityTypeMockMvc.perform(get("/api/commodity-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommodityType() throws Exception {
        // Initialize the database
        commodityTypeRepository.saveAndFlush(commodityType);

        int databaseSizeBeforeUpdate = commodityTypeRepository.findAll().size();

        // Update the commodityType
        CommodityType updatedCommodityType = commodityTypeRepository.findById(commodityType.getId()).get();
        // Disconnect from session so that the updates on updatedCommodityType are not directly saved in db
        em.detach(updatedCommodityType);
        updatedCommodityType
            .name(UPDATED_NAME);
        CommodityTypeDTO commodityTypeDTO = commodityTypeMapper.toDto(updatedCommodityType);

        restCommodityTypeMockMvc.perform(put("/api/commodity-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commodityTypeDTO)))
            .andExpect(status().isOk());

        // Validate the CommodityType in the database
        List<CommodityType> commodityTypeList = commodityTypeRepository.findAll();
        assertThat(commodityTypeList).hasSize(databaseSizeBeforeUpdate);
        CommodityType testCommodityType = commodityTypeList.get(commodityTypeList.size() - 1);
        assertThat(testCommodityType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCommodityType() throws Exception {
        int databaseSizeBeforeUpdate = commodityTypeRepository.findAll().size();

        // Create the CommodityType
        CommodityTypeDTO commodityTypeDTO = commodityTypeMapper.toDto(commodityType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommodityTypeMockMvc.perform(put("/api/commodity-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commodityTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommodityType in the database
        List<CommodityType> commodityTypeList = commodityTypeRepository.findAll();
        assertThat(commodityTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommodityType() throws Exception {
        // Initialize the database
        commodityTypeRepository.saveAndFlush(commodityType);

        int databaseSizeBeforeDelete = commodityTypeRepository.findAll().size();

        // Delete the commodityType
        restCommodityTypeMockMvc.perform(delete("/api/commodity-types/{id}", commodityType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CommodityType> commodityTypeList = commodityTypeRepository.findAll();
        assertThat(commodityTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
