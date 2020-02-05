package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.CustomsTaxBillTo;
import com.eshipper.repository.CustomsTaxBillToRepository;
import com.eshipper.service.CustomsTaxBillToService;
import com.eshipper.service.dto.CustomsTaxBillToDTO;
import com.eshipper.service.mapper.CustomsTaxBillToMapper;
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
 * Integration tests for the {@link CustomsTaxBillToResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class CustomsTaxBillToResourceIT {

    private static final String DEFAULT_BILL_TO = "AAAAAAAAAA";
    private static final String UPDATED_BILL_TO = "BBBBBBBBBB";

    @Autowired
    private CustomsTaxBillToRepository customsTaxBillToRepository;

    @Autowired
    private CustomsTaxBillToMapper customsTaxBillToMapper;

    @Autowired
    private CustomsTaxBillToService customsTaxBillToService;

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

    private MockMvc restCustomsTaxBillToMockMvc;

    private CustomsTaxBillTo customsTaxBillTo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomsTaxBillToResource customsTaxBillToResource = new CustomsTaxBillToResource(customsTaxBillToService);
        this.restCustomsTaxBillToMockMvc = MockMvcBuilders.standaloneSetup(customsTaxBillToResource)
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
    public static CustomsTaxBillTo createEntity(EntityManager em) {
        CustomsTaxBillTo customsTaxBillTo = new CustomsTaxBillTo()
            .billTo(DEFAULT_BILL_TO);
        return customsTaxBillTo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomsTaxBillTo createUpdatedEntity(EntityManager em) {
        CustomsTaxBillTo customsTaxBillTo = new CustomsTaxBillTo()
            .billTo(UPDATED_BILL_TO);
        return customsTaxBillTo;
    }

    @BeforeEach
    public void initTest() {
        customsTaxBillTo = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomsTaxBillTo() throws Exception {
        int databaseSizeBeforeCreate = customsTaxBillToRepository.findAll().size();

        // Create the CustomsTaxBillTo
        CustomsTaxBillToDTO customsTaxBillToDTO = customsTaxBillToMapper.toDto(customsTaxBillTo);
        restCustomsTaxBillToMockMvc.perform(post("/api/customs-tax-bill-tos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customsTaxBillToDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomsTaxBillTo in the database
        List<CustomsTaxBillTo> customsTaxBillToList = customsTaxBillToRepository.findAll();
        assertThat(customsTaxBillToList).hasSize(databaseSizeBeforeCreate + 1);
        CustomsTaxBillTo testCustomsTaxBillTo = customsTaxBillToList.get(customsTaxBillToList.size() - 1);
        assertThat(testCustomsTaxBillTo.getBillTo()).isEqualTo(DEFAULT_BILL_TO);
    }

    @Test
    @Transactional
    public void createCustomsTaxBillToWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customsTaxBillToRepository.findAll().size();

        // Create the CustomsTaxBillTo with an existing ID
        customsTaxBillTo.setId(1L);
        CustomsTaxBillToDTO customsTaxBillToDTO = customsTaxBillToMapper.toDto(customsTaxBillTo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomsTaxBillToMockMvc.perform(post("/api/customs-tax-bill-tos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customsTaxBillToDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomsTaxBillTo in the database
        List<CustomsTaxBillTo> customsTaxBillToList = customsTaxBillToRepository.findAll();
        assertThat(customsTaxBillToList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCustomsTaxBillTos() throws Exception {
        // Initialize the database
        customsTaxBillToRepository.saveAndFlush(customsTaxBillTo);

        // Get all the customsTaxBillToList
        restCustomsTaxBillToMockMvc.perform(get("/api/customs-tax-bill-tos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customsTaxBillTo.getId().intValue())))
            .andExpect(jsonPath("$.[*].billTo").value(hasItem(DEFAULT_BILL_TO)));
    }
    
    @Test
    @Transactional
    public void getCustomsTaxBillTo() throws Exception {
        // Initialize the database
        customsTaxBillToRepository.saveAndFlush(customsTaxBillTo);

        // Get the customsTaxBillTo
        restCustomsTaxBillToMockMvc.perform(get("/api/customs-tax-bill-tos/{id}", customsTaxBillTo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customsTaxBillTo.getId().intValue()))
            .andExpect(jsonPath("$.billTo").value(DEFAULT_BILL_TO));
    }

    @Test
    @Transactional
    public void getNonExistingCustomsTaxBillTo() throws Exception {
        // Get the customsTaxBillTo
        restCustomsTaxBillToMockMvc.perform(get("/api/customs-tax-bill-tos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomsTaxBillTo() throws Exception {
        // Initialize the database
        customsTaxBillToRepository.saveAndFlush(customsTaxBillTo);

        int databaseSizeBeforeUpdate = customsTaxBillToRepository.findAll().size();

        // Update the customsTaxBillTo
        CustomsTaxBillTo updatedCustomsTaxBillTo = customsTaxBillToRepository.findById(customsTaxBillTo.getId()).get();
        // Disconnect from session so that the updates on updatedCustomsTaxBillTo are not directly saved in db
        em.detach(updatedCustomsTaxBillTo);
        updatedCustomsTaxBillTo
            .billTo(UPDATED_BILL_TO);
        CustomsTaxBillToDTO customsTaxBillToDTO = customsTaxBillToMapper.toDto(updatedCustomsTaxBillTo);

        restCustomsTaxBillToMockMvc.perform(put("/api/customs-tax-bill-tos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customsTaxBillToDTO)))
            .andExpect(status().isOk());

        // Validate the CustomsTaxBillTo in the database
        List<CustomsTaxBillTo> customsTaxBillToList = customsTaxBillToRepository.findAll();
        assertThat(customsTaxBillToList).hasSize(databaseSizeBeforeUpdate);
        CustomsTaxBillTo testCustomsTaxBillTo = customsTaxBillToList.get(customsTaxBillToList.size() - 1);
        assertThat(testCustomsTaxBillTo.getBillTo()).isEqualTo(UPDATED_BILL_TO);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomsTaxBillTo() throws Exception {
        int databaseSizeBeforeUpdate = customsTaxBillToRepository.findAll().size();

        // Create the CustomsTaxBillTo
        CustomsTaxBillToDTO customsTaxBillToDTO = customsTaxBillToMapper.toDto(customsTaxBillTo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomsTaxBillToMockMvc.perform(put("/api/customs-tax-bill-tos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customsTaxBillToDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomsTaxBillTo in the database
        List<CustomsTaxBillTo> customsTaxBillToList = customsTaxBillToRepository.findAll();
        assertThat(customsTaxBillToList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomsTaxBillTo() throws Exception {
        // Initialize the database
        customsTaxBillToRepository.saveAndFlush(customsTaxBillTo);

        int databaseSizeBeforeDelete = customsTaxBillToRepository.findAll().size();

        // Delete the customsTaxBillTo
        restCustomsTaxBillToMockMvc.perform(delete("/api/customs-tax-bill-tos/{id}", customsTaxBillTo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustomsTaxBillTo> customsTaxBillToList = customsTaxBillToRepository.findAll();
        assertThat(customsTaxBillToList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
