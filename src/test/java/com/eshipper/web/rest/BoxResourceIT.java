package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.Box;
import com.eshipper.repository.BoxRepository;
import com.eshipper.service.BoxService;
import com.eshipper.service.dto.BoxDTO;
import com.eshipper.service.mapper.BoxMapper;
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
 * Integration tests for the {@link BoxResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class BoxResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAX_SUPPORT_WEIGHT = 20;
    private static final Integer UPDATED_MAX_SUPPORT_WEIGHT = 19;
    private static final Integer SMALLER_MAX_SUPPORT_WEIGHT = 20 - 1;

    private static final Integer DEFAULT_LENGTH = 20;
    private static final Integer UPDATED_LENGTH = 19;
    private static final Integer SMALLER_LENGTH = 20 - 1;

    private static final Integer DEFAULT_WIDTH = 20;
    private static final Integer UPDATED_WIDTH = 19;
    private static final Integer SMALLER_WIDTH = 20 - 1;

    private static final Integer DEFAULT_HEIGHT = 20;
    private static final Integer UPDATED_HEIGHT = 19;
    private static final Integer SMALLER_HEIGHT = 20 - 1;

    private static final Integer DEFAULT_WEIGHT = 20;
    private static final Integer UPDATED_WEIGHT = 19;
    private static final Integer SMALLER_WEIGHT = 20 - 1;

    @Autowired
    private BoxRepository boxRepository;

    @Autowired
    private BoxMapper boxMapper;

    @Autowired
    private BoxService boxService;

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

    private MockMvc restBoxMockMvc;

    private Box box;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BoxResource boxResource = new BoxResource(boxService);
        this.restBoxMockMvc = MockMvcBuilders.standaloneSetup(boxResource)
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
    public static Box createEntity(EntityManager em) {
        Box box = new Box()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .maxSupportWeight(DEFAULT_MAX_SUPPORT_WEIGHT)
            .length(DEFAULT_LENGTH)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT)
            .weight(DEFAULT_WEIGHT);
        return box;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Box createUpdatedEntity(EntityManager em) {
        Box box = new Box()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .maxSupportWeight(UPDATED_MAX_SUPPORT_WEIGHT)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT);
        return box;
    }

    @BeforeEach
    public void initTest() {
        box = createEntity(em);
    }

    @Test
    @Transactional
    public void createBox() throws Exception {
        int databaseSizeBeforeCreate = boxRepository.findAll().size();

        // Create the Box
        BoxDTO boxDTO = boxMapper.toDto(box);
        restBoxMockMvc.perform(post("/api/boxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boxDTO)))
            .andExpect(status().isCreated());

        // Validate the Box in the database
        List<Box> boxList = boxRepository.findAll();
        assertThat(boxList).hasSize(databaseSizeBeforeCreate + 1);
        Box testBox = boxList.get(boxList.size() - 1);
        assertThat(testBox.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBox.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBox.getMaxSupportWeight()).isEqualTo(DEFAULT_MAX_SUPPORT_WEIGHT);
        assertThat(testBox.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testBox.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testBox.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testBox.getWeight()).isEqualTo(DEFAULT_WEIGHT);
    }

    @Test
    @Transactional
    public void createBoxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = boxRepository.findAll().size();

        // Create the Box with an existing ID
        box.setId(1L);
        BoxDTO boxDTO = boxMapper.toDto(box);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBoxMockMvc.perform(post("/api/boxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Box in the database
        List<Box> boxList = boxRepository.findAll();
        assertThat(boxList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBoxes() throws Exception {
        // Initialize the database
        boxRepository.saveAndFlush(box);

        // Get all the boxList
        restBoxMockMvc.perform(get("/api/boxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(box.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].maxSupportWeight").value(hasItem(DEFAULT_MAX_SUPPORT_WEIGHT)))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)));
    }
    
    @Test
    @Transactional
    public void getBox() throws Exception {
        // Initialize the database
        boxRepository.saveAndFlush(box);

        // Get the box
        restBoxMockMvc.perform(get("/api/boxes/{id}", box.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(box.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.maxSupportWeight").value(DEFAULT_MAX_SUPPORT_WEIGHT))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT));
    }

    @Test
    @Transactional
    public void getNonExistingBox() throws Exception {
        // Get the box
        restBoxMockMvc.perform(get("/api/boxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBox() throws Exception {
        // Initialize the database
        boxRepository.saveAndFlush(box);

        int databaseSizeBeforeUpdate = boxRepository.findAll().size();

        // Update the box
        Box updatedBox = boxRepository.findById(box.getId()).get();
        // Disconnect from session so that the updates on updatedBox are not directly saved in db
        em.detach(updatedBox);
        updatedBox
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .maxSupportWeight(UPDATED_MAX_SUPPORT_WEIGHT)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT);
        BoxDTO boxDTO = boxMapper.toDto(updatedBox);

        restBoxMockMvc.perform(put("/api/boxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boxDTO)))
            .andExpect(status().isOk());

        // Validate the Box in the database
        List<Box> boxList = boxRepository.findAll();
        assertThat(boxList).hasSize(databaseSizeBeforeUpdate);
        Box testBox = boxList.get(boxList.size() - 1);
        assertThat(testBox.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBox.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBox.getMaxSupportWeight()).isEqualTo(UPDATED_MAX_SUPPORT_WEIGHT);
        assertThat(testBox.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testBox.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testBox.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testBox.getWeight()).isEqualTo(UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void updateNonExistingBox() throws Exception {
        int databaseSizeBeforeUpdate = boxRepository.findAll().size();

        // Create the Box
        BoxDTO boxDTO = boxMapper.toDto(box);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBoxMockMvc.perform(put("/api/boxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Box in the database
        List<Box> boxList = boxRepository.findAll();
        assertThat(boxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBox() throws Exception {
        // Initialize the database
        boxRepository.saveAndFlush(box);

        int databaseSizeBeforeDelete = boxRepository.findAll().size();

        // Delete the box
        restBoxMockMvc.perform(delete("/api/boxes/{id}", box.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Box> boxList = boxRepository.findAll();
        assertThat(boxList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Box.class);
        Box box1 = new Box();
        box1.setId(1L);
        Box box2 = new Box();
        box2.setId(box1.getId());
        assertThat(box1).isEqualTo(box2);
        box2.setId(2L);
        assertThat(box1).isNotEqualTo(box2);
        box1.setId(null);
        assertThat(box1).isNotEqualTo(box2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BoxDTO.class);
        BoxDTO boxDTO1 = new BoxDTO();
        boxDTO1.setId(1L);
        BoxDTO boxDTO2 = new BoxDTO();
        assertThat(boxDTO1).isNotEqualTo(boxDTO2);
        boxDTO2.setId(boxDTO1.getId());
        assertThat(boxDTO1).isEqualTo(boxDTO2);
        boxDTO2.setId(2L);
        assertThat(boxDTO1).isNotEqualTo(boxDTO2);
        boxDTO1.setId(null);
        assertThat(boxDTO1).isNotEqualTo(boxDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(boxMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(boxMapper.fromId(null)).isNull();
    }
}
