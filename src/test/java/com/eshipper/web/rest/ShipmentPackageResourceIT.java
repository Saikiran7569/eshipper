package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ShipmentPackage;
import com.eshipper.repository.ShipmentPackageRepository;
import com.eshipper.service.ShipmentPackageService;
import com.eshipper.service.dto.ShipmentPackageDTO;
import com.eshipper.service.mapper.ShipmentPackageMapper;
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
 * Integration tests for the {@link ShipmentPackageResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class ShipmentPackageResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_LENGTH = 11;
    private static final Integer UPDATED_LENGTH = 10;

    private static final Integer DEFAULT_WIDTH = 11;
    private static final Integer UPDATED_WIDTH = 10;

    private static final Integer DEFAULT_HEIGHT = 11;
    private static final Integer UPDATED_HEIGHT = 10;

    private static final Integer DEFAULT_WEIGHT = 11;
    private static final Integer UPDATED_WEIGHT = 10;

    private static final Integer DEFAULT_POSITION = 11;
    private static final Integer UPDATED_POSITION = 10;

    private static final String DEFAULT_TRACKING_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TRACKING_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_CUBED_WEIGHT = 11;
    private static final Integer UPDATED_CUBED_WEIGHT = 10;

    private static final Float DEFAULT_COD_VALUE = 1F;
    private static final Float UPDATED_COD_VALUE = 2F;

    private static final Float DEFAULT_INSURANCE_AMOUNT = 1F;
    private static final Float UPDATED_INSURANCE_AMOUNT = 2F;

    private static final String DEFAULT_FREIGHT_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_FREIGHT_CLASS = "BBBBBBBBBB";

    private static final String DEFAULT_NMFC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_NMFC_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_WEIGHT_OZ = 11;
    private static final Integer UPDATED_WEIGHT_OZ = 10;

    private static final Float DEFAULT_ITEM_VALUE = 1F;
    private static final Float UPDATED_ITEM_VALUE = 2F;

    private static final String DEFAULT_HARMONIZED_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HARMONIZED_CODE = "BBBBBBBBBB";

    @Autowired
    private ShipmentPackageRepository shipmentPackageRepository;

    @Autowired
    private ShipmentPackageMapper shipmentPackageMapper;

    @Autowired
    private ShipmentPackageService shipmentPackageService;

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

    private MockMvc restShipmentPackageMockMvc;

    private ShipmentPackage shipmentPackage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShipmentPackageResource shipmentPackageResource = new ShipmentPackageResource(shipmentPackageService);
        this.restShipmentPackageMockMvc = MockMvcBuilders.standaloneSetup(shipmentPackageResource)
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
    public static ShipmentPackage createEntity(EntityManager em) {
        ShipmentPackage shipmentPackage = new ShipmentPackage()
            .description(DEFAULT_DESCRIPTION)
            .length(DEFAULT_LENGTH)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT)
            .weight(DEFAULT_WEIGHT)
            .position(DEFAULT_POSITION)
            .trackingNumber(DEFAULT_TRACKING_NUMBER)
            .cubedWeight(DEFAULT_CUBED_WEIGHT)
            .codValue(DEFAULT_COD_VALUE)
            .insuranceAmount(DEFAULT_INSURANCE_AMOUNT)
            .freightClass(DEFAULT_FREIGHT_CLASS)
            .nmfcCode(DEFAULT_NMFC_CODE)
            .weightOz(DEFAULT_WEIGHT_OZ)
            .itemValue(DEFAULT_ITEM_VALUE)
            .harmonizedCode(DEFAULT_HARMONIZED_CODE);
        return shipmentPackage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShipmentPackage createUpdatedEntity(EntityManager em) {
        ShipmentPackage shipmentPackage = new ShipmentPackage()
            .description(UPDATED_DESCRIPTION)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .position(UPDATED_POSITION)
            .trackingNumber(UPDATED_TRACKING_NUMBER)
            .cubedWeight(UPDATED_CUBED_WEIGHT)
            .codValue(UPDATED_COD_VALUE)
            .insuranceAmount(UPDATED_INSURANCE_AMOUNT)
            .freightClass(UPDATED_FREIGHT_CLASS)
            .nmfcCode(UPDATED_NMFC_CODE)
            .weightOz(UPDATED_WEIGHT_OZ)
            .itemValue(UPDATED_ITEM_VALUE)
            .harmonizedCode(UPDATED_HARMONIZED_CODE);
        return shipmentPackage;
    }

    @BeforeEach
    public void initTest() {
        shipmentPackage = createEntity(em);
    }

    @Test
    @Transactional
    public void createShipmentPackage() throws Exception {
        int databaseSizeBeforeCreate = shipmentPackageRepository.findAll().size();

        // Create the ShipmentPackage
        ShipmentPackageDTO shipmentPackageDTO = shipmentPackageMapper.toDto(shipmentPackage);
        restShipmentPackageMockMvc.perform(post("/api/shipment-packages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shipmentPackageDTO)))
            .andExpect(status().isCreated());

        // Validate the ShipmentPackage in the database
        List<ShipmentPackage> shipmentPackageList = shipmentPackageRepository.findAll();
        assertThat(shipmentPackageList).hasSize(databaseSizeBeforeCreate + 1);
        ShipmentPackage testShipmentPackage = shipmentPackageList.get(shipmentPackageList.size() - 1);
        assertThat(testShipmentPackage.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testShipmentPackage.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testShipmentPackage.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testShipmentPackage.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testShipmentPackage.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testShipmentPackage.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testShipmentPackage.getTrackingNumber()).isEqualTo(DEFAULT_TRACKING_NUMBER);
        assertThat(testShipmentPackage.getCubedWeight()).isEqualTo(DEFAULT_CUBED_WEIGHT);
        assertThat(testShipmentPackage.getCodValue()).isEqualTo(DEFAULT_COD_VALUE);
        assertThat(testShipmentPackage.getInsuranceAmount()).isEqualTo(DEFAULT_INSURANCE_AMOUNT);
        assertThat(testShipmentPackage.getFreightClass()).isEqualTo(DEFAULT_FREIGHT_CLASS);
        assertThat(testShipmentPackage.getNmfcCode()).isEqualTo(DEFAULT_NMFC_CODE);
        assertThat(testShipmentPackage.getWeightOz()).isEqualTo(DEFAULT_WEIGHT_OZ);
        assertThat(testShipmentPackage.getItemValue()).isEqualTo(DEFAULT_ITEM_VALUE);
        assertThat(testShipmentPackage.getHarmonizedCode()).isEqualTo(DEFAULT_HARMONIZED_CODE);
    }

    @Test
    @Transactional
    public void createShipmentPackageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shipmentPackageRepository.findAll().size();

        // Create the ShipmentPackage with an existing ID
        shipmentPackage.setId(1L);
        ShipmentPackageDTO shipmentPackageDTO = shipmentPackageMapper.toDto(shipmentPackage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShipmentPackageMockMvc.perform(post("/api/shipment-packages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shipmentPackageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShipmentPackage in the database
        List<ShipmentPackage> shipmentPackageList = shipmentPackageRepository.findAll();
        assertThat(shipmentPackageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllShipmentPackages() throws Exception {
        // Initialize the database
        shipmentPackageRepository.saveAndFlush(shipmentPackage);

        // Get all the shipmentPackageList
        restShipmentPackageMockMvc.perform(get("/api/shipment-packages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shipmentPackage.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].trackingNumber").value(hasItem(DEFAULT_TRACKING_NUMBER)))
            .andExpect(jsonPath("$.[*].cubedWeight").value(hasItem(DEFAULT_CUBED_WEIGHT)))
            .andExpect(jsonPath("$.[*].codValue").value(hasItem(DEFAULT_COD_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].insuranceAmount").value(hasItem(DEFAULT_INSURANCE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].freightClass").value(hasItem(DEFAULT_FREIGHT_CLASS)))
            .andExpect(jsonPath("$.[*].nmfcCode").value(hasItem(DEFAULT_NMFC_CODE)))
            .andExpect(jsonPath("$.[*].weightOz").value(hasItem(DEFAULT_WEIGHT_OZ)))
            .andExpect(jsonPath("$.[*].itemValue").value(hasItem(DEFAULT_ITEM_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].harmonizedCode").value(hasItem(DEFAULT_HARMONIZED_CODE)));
    }
    
    @Test
    @Transactional
    public void getShipmentPackage() throws Exception {
        // Initialize the database
        shipmentPackageRepository.saveAndFlush(shipmentPackage);

        // Get the shipmentPackage
        restShipmentPackageMockMvc.perform(get("/api/shipment-packages/{id}", shipmentPackage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(shipmentPackage.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.trackingNumber").value(DEFAULT_TRACKING_NUMBER))
            .andExpect(jsonPath("$.cubedWeight").value(DEFAULT_CUBED_WEIGHT))
            .andExpect(jsonPath("$.codValue").value(DEFAULT_COD_VALUE.doubleValue()))
            .andExpect(jsonPath("$.insuranceAmount").value(DEFAULT_INSURANCE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.freightClass").value(DEFAULT_FREIGHT_CLASS))
            .andExpect(jsonPath("$.nmfcCode").value(DEFAULT_NMFC_CODE))
            .andExpect(jsonPath("$.weightOz").value(DEFAULT_WEIGHT_OZ))
            .andExpect(jsonPath("$.itemValue").value(DEFAULT_ITEM_VALUE.doubleValue()))
            .andExpect(jsonPath("$.harmonizedCode").value(DEFAULT_HARMONIZED_CODE));
    }

    @Test
    @Transactional
    public void getNonExistingShipmentPackage() throws Exception {
        // Get the shipmentPackage
        restShipmentPackageMockMvc.perform(get("/api/shipment-packages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShipmentPackage() throws Exception {
        // Initialize the database
        shipmentPackageRepository.saveAndFlush(shipmentPackage);

        int databaseSizeBeforeUpdate = shipmentPackageRepository.findAll().size();

        // Update the shipmentPackage
        ShipmentPackage updatedShipmentPackage = shipmentPackageRepository.findById(shipmentPackage.getId()).get();
        // Disconnect from session so that the updates on updatedShipmentPackage are not directly saved in db
        em.detach(updatedShipmentPackage);
        updatedShipmentPackage
            .description(UPDATED_DESCRIPTION)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .position(UPDATED_POSITION)
            .trackingNumber(UPDATED_TRACKING_NUMBER)
            .cubedWeight(UPDATED_CUBED_WEIGHT)
            .codValue(UPDATED_COD_VALUE)
            .insuranceAmount(UPDATED_INSURANCE_AMOUNT)
            .freightClass(UPDATED_FREIGHT_CLASS)
            .nmfcCode(UPDATED_NMFC_CODE)
            .weightOz(UPDATED_WEIGHT_OZ)
            .itemValue(UPDATED_ITEM_VALUE)
            .harmonizedCode(UPDATED_HARMONIZED_CODE);
        ShipmentPackageDTO shipmentPackageDTO = shipmentPackageMapper.toDto(updatedShipmentPackage);

        restShipmentPackageMockMvc.perform(put("/api/shipment-packages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shipmentPackageDTO)))
            .andExpect(status().isOk());

        // Validate the ShipmentPackage in the database
        List<ShipmentPackage> shipmentPackageList = shipmentPackageRepository.findAll();
        assertThat(shipmentPackageList).hasSize(databaseSizeBeforeUpdate);
        ShipmentPackage testShipmentPackage = shipmentPackageList.get(shipmentPackageList.size() - 1);
        assertThat(testShipmentPackage.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testShipmentPackage.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testShipmentPackage.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testShipmentPackage.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testShipmentPackage.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testShipmentPackage.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testShipmentPackage.getTrackingNumber()).isEqualTo(UPDATED_TRACKING_NUMBER);
        assertThat(testShipmentPackage.getCubedWeight()).isEqualTo(UPDATED_CUBED_WEIGHT);
        assertThat(testShipmentPackage.getCodValue()).isEqualTo(UPDATED_COD_VALUE);
        assertThat(testShipmentPackage.getInsuranceAmount()).isEqualTo(UPDATED_INSURANCE_AMOUNT);
        assertThat(testShipmentPackage.getFreightClass()).isEqualTo(UPDATED_FREIGHT_CLASS);
        assertThat(testShipmentPackage.getNmfcCode()).isEqualTo(UPDATED_NMFC_CODE);
        assertThat(testShipmentPackage.getWeightOz()).isEqualTo(UPDATED_WEIGHT_OZ);
        assertThat(testShipmentPackage.getItemValue()).isEqualTo(UPDATED_ITEM_VALUE);
        assertThat(testShipmentPackage.getHarmonizedCode()).isEqualTo(UPDATED_HARMONIZED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingShipmentPackage() throws Exception {
        int databaseSizeBeforeUpdate = shipmentPackageRepository.findAll().size();

        // Create the ShipmentPackage
        ShipmentPackageDTO shipmentPackageDTO = shipmentPackageMapper.toDto(shipmentPackage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShipmentPackageMockMvc.perform(put("/api/shipment-packages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shipmentPackageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShipmentPackage in the database
        List<ShipmentPackage> shipmentPackageList = shipmentPackageRepository.findAll();
        assertThat(shipmentPackageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShipmentPackage() throws Exception {
        // Initialize the database
        shipmentPackageRepository.saveAndFlush(shipmentPackage);

        int databaseSizeBeforeDelete = shipmentPackageRepository.findAll().size();

        // Delete the shipmentPackage
        restShipmentPackageMockMvc.perform(delete("/api/shipment-packages/{id}", shipmentPackage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShipmentPackage> shipmentPackageList = shipmentPackageRepository.findAll();
        assertThat(shipmentPackageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShipmentPackage.class);
        ShipmentPackage shipmentPackage1 = new ShipmentPackage();
        shipmentPackage1.setId(1L);
        ShipmentPackage shipmentPackage2 = new ShipmentPackage();
        shipmentPackage2.setId(shipmentPackage1.getId());
        assertThat(shipmentPackage1).isEqualTo(shipmentPackage2);
        shipmentPackage2.setId(2L);
        assertThat(shipmentPackage1).isNotEqualTo(shipmentPackage2);
        shipmentPackage1.setId(null);
        assertThat(shipmentPackage1).isNotEqualTo(shipmentPackage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShipmentPackageDTO.class);
        ShipmentPackageDTO shipmentPackageDTO1 = new ShipmentPackageDTO();
        shipmentPackageDTO1.setId(1L);
        ShipmentPackageDTO shipmentPackageDTO2 = new ShipmentPackageDTO();
        assertThat(shipmentPackageDTO1).isNotEqualTo(shipmentPackageDTO2);
        shipmentPackageDTO2.setId(shipmentPackageDTO1.getId());
        assertThat(shipmentPackageDTO1).isEqualTo(shipmentPackageDTO2);
        shipmentPackageDTO2.setId(2L);
        assertThat(shipmentPackageDTO1).isNotEqualTo(shipmentPackageDTO2);
        shipmentPackageDTO1.setId(null);
        assertThat(shipmentPackageDTO1).isNotEqualTo(shipmentPackageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(shipmentPackageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(shipmentPackageMapper.fromId(null)).isNull();
    }
}
