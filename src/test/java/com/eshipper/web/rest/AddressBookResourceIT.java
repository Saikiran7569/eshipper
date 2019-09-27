package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.AddressBook;
import com.eshipper.repository.AddressBookRepository;
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
 * Integration tests for the {@link AddressBookResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
public class AddressBookResourceIT {

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NO = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NOTIFY = false;
    private static final Boolean UPDATED_NOTIFY = true;

    private static final Boolean DEFAULT_RESIDENTIAL = false;
    private static final Boolean UPDATED_RESIDENTIAL = true;

    private static final String DEFAULT_CREATED_BY_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_USER = "BBBBBBBBBB";

    private static final String DEFAULT_INSTRUCTION = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUCTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_CREATED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CREATED = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_CREATED = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_DATE_UPDATED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_UPDATED = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_UPDATED = LocalDate.ofEpochDay(-1L);

    @Autowired
    private AddressBookRepository addressBookRepository;

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

    private MockMvc restAddressBookMockMvc;

    private AddressBook addressBook;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AddressBookResource addressBookResource = new AddressBookResource(addressBookRepository);
        this.restAddressBookMockMvc = MockMvcBuilders.standaloneSetup(addressBookResource)
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
    public static AddressBook createEntity(EntityManager em) {
        AddressBook addressBook = new AddressBook()
            .companyName(DEFAULT_COMPANY_NAME)
            .address1(DEFAULT_ADDRESS_1)
            .address2(DEFAULT_ADDRESS_2)
            .postalCode(DEFAULT_POSTAL_CODE)
            .contactName(DEFAULT_CONTACT_NAME)
            .phoneNo(DEFAULT_PHONE_NO)
            .contactEmail(DEFAULT_CONTACT_EMAIL)
            .province(DEFAULT_PROVINCE)
            .notify(DEFAULT_NOTIFY)
            .residential(DEFAULT_RESIDENTIAL)
            .createdByUser(DEFAULT_CREATED_BY_USER)
            .instruction(DEFAULT_INSTRUCTION)
            .dateCreated(DEFAULT_DATE_CREATED)
            .dateUpdated(DEFAULT_DATE_UPDATED);
        return addressBook;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AddressBook createUpdatedEntity(EntityManager em) {
        AddressBook addressBook = new AddressBook()
            .companyName(UPDATED_COMPANY_NAME)
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .postalCode(UPDATED_POSTAL_CODE)
            .contactName(UPDATED_CONTACT_NAME)
            .phoneNo(UPDATED_PHONE_NO)
            .contactEmail(UPDATED_CONTACT_EMAIL)
            .province(UPDATED_PROVINCE)
            .notify(UPDATED_NOTIFY)
            .residential(UPDATED_RESIDENTIAL)
            .createdByUser(UPDATED_CREATED_BY_USER)
            .instruction(UPDATED_INSTRUCTION)
            .dateCreated(UPDATED_DATE_CREATED)
            .dateUpdated(UPDATED_DATE_UPDATED);
        return addressBook;
    }

    @BeforeEach
    public void initTest() {
        addressBook = createEntity(em);
    }

    @Test
    @Transactional
    public void createAddressBook() throws Exception {
        int databaseSizeBeforeCreate = addressBookRepository.findAll().size();

        // Create the AddressBook
        restAddressBookMockMvc.perform(post("/api/address-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressBook)))
            .andExpect(status().isCreated());

        // Validate the AddressBook in the database
        List<AddressBook> addressBookList = addressBookRepository.findAll();
        assertThat(addressBookList).hasSize(databaseSizeBeforeCreate + 1);
        AddressBook testAddressBook = addressBookList.get(addressBookList.size() - 1);
        assertThat(testAddressBook.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testAddressBook.getAddress1()).isEqualTo(DEFAULT_ADDRESS_1);
        assertThat(testAddressBook.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testAddressBook.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testAddressBook.getContactName()).isEqualTo(DEFAULT_CONTACT_NAME);
        assertThat(testAddressBook.getPhoneNo()).isEqualTo(DEFAULT_PHONE_NO);
        assertThat(testAddressBook.getContactEmail()).isEqualTo(DEFAULT_CONTACT_EMAIL);
        assertThat(testAddressBook.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testAddressBook.isNotify()).isEqualTo(DEFAULT_NOTIFY);
        assertThat(testAddressBook.isResidential()).isEqualTo(DEFAULT_RESIDENTIAL);
        assertThat(testAddressBook.getCreatedByUser()).isEqualTo(DEFAULT_CREATED_BY_USER);
        assertThat(testAddressBook.getInstruction()).isEqualTo(DEFAULT_INSTRUCTION);
        assertThat(testAddressBook.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testAddressBook.getDateUpdated()).isEqualTo(DEFAULT_DATE_UPDATED);
    }

    @Test
    @Transactional
    public void createAddressBookWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = addressBookRepository.findAll().size();

        // Create the AddressBook with an existing ID
        addressBook.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddressBookMockMvc.perform(post("/api/address-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressBook)))
            .andExpect(status().isBadRequest());

        // Validate the AddressBook in the database
        List<AddressBook> addressBookList = addressBookRepository.findAll();
        assertThat(addressBookList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAddressBooks() throws Exception {
        // Initialize the database
        addressBookRepository.saveAndFlush(addressBook);

        // Get all the addressBookList
        restAddressBookMockMvc.perform(get("/api/address-books?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(addressBook.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].address1").value(hasItem(DEFAULT_ADDRESS_1.toString())))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2.toString())))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE.toString())))
            .andExpect(jsonPath("$.[*].contactName").value(hasItem(DEFAULT_CONTACT_NAME.toString())))
            .andExpect(jsonPath("$.[*].phoneNo").value(hasItem(DEFAULT_PHONE_NO.toString())))
            .andExpect(jsonPath("$.[*].contactEmail").value(hasItem(DEFAULT_CONTACT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].notify").value(hasItem(DEFAULT_NOTIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].residential").value(hasItem(DEFAULT_RESIDENTIAL.booleanValue())))
            .andExpect(jsonPath("$.[*].createdByUser").value(hasItem(DEFAULT_CREATED_BY_USER.toString())))
            .andExpect(jsonPath("$.[*].instruction").value(hasItem(DEFAULT_INSTRUCTION.toString())))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED.toString())))
            .andExpect(jsonPath("$.[*].dateUpdated").value(hasItem(DEFAULT_DATE_UPDATED.toString())));
    }
    
    @Test
    @Transactional
    public void getAddressBook() throws Exception {
        // Initialize the database
        addressBookRepository.saveAndFlush(addressBook);

        // Get the addressBook
        restAddressBookMockMvc.perform(get("/api/address-books/{id}", addressBook.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(addressBook.getId().intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.address1").value(DEFAULT_ADDRESS_1.toString()))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2.toString()))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.contactName").value(DEFAULT_CONTACT_NAME.toString()))
            .andExpect(jsonPath("$.phoneNo").value(DEFAULT_PHONE_NO.toString()))
            .andExpect(jsonPath("$.contactEmail").value(DEFAULT_CONTACT_EMAIL.toString()))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE.toString()))
            .andExpect(jsonPath("$.notify").value(DEFAULT_NOTIFY.booleanValue()))
            .andExpect(jsonPath("$.residential").value(DEFAULT_RESIDENTIAL.booleanValue()))
            .andExpect(jsonPath("$.createdByUser").value(DEFAULT_CREATED_BY_USER.toString()))
            .andExpect(jsonPath("$.instruction").value(DEFAULT_INSTRUCTION.toString()))
            .andExpect(jsonPath("$.dateCreated").value(DEFAULT_DATE_CREATED.toString()))
            .andExpect(jsonPath("$.dateUpdated").value(DEFAULT_DATE_UPDATED.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAddressBook() throws Exception {
        // Get the addressBook
        restAddressBookMockMvc.perform(get("/api/address-books/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAddressBook() throws Exception {
        // Initialize the database
        addressBookRepository.saveAndFlush(addressBook);

        int databaseSizeBeforeUpdate = addressBookRepository.findAll().size();

        // Update the addressBook
        AddressBook updatedAddressBook = addressBookRepository.findById(addressBook.getId()).get();
        // Disconnect from session so that the updates on updatedAddressBook are not directly saved in db
        em.detach(updatedAddressBook);
        updatedAddressBook
            .companyName(UPDATED_COMPANY_NAME)
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .postalCode(UPDATED_POSTAL_CODE)
            .contactName(UPDATED_CONTACT_NAME)
            .phoneNo(UPDATED_PHONE_NO)
            .contactEmail(UPDATED_CONTACT_EMAIL)
            .province(UPDATED_PROVINCE)
            .notify(UPDATED_NOTIFY)
            .residential(UPDATED_RESIDENTIAL)
            .createdByUser(UPDATED_CREATED_BY_USER)
            .instruction(UPDATED_INSTRUCTION)
            .dateCreated(UPDATED_DATE_CREATED)
            .dateUpdated(UPDATED_DATE_UPDATED);

        restAddressBookMockMvc.perform(put("/api/address-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAddressBook)))
            .andExpect(status().isOk());

        // Validate the AddressBook in the database
        List<AddressBook> addressBookList = addressBookRepository.findAll();
        assertThat(addressBookList).hasSize(databaseSizeBeforeUpdate);
        AddressBook testAddressBook = addressBookList.get(addressBookList.size() - 1);
        assertThat(testAddressBook.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testAddressBook.getAddress1()).isEqualTo(UPDATED_ADDRESS_1);
        assertThat(testAddressBook.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testAddressBook.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testAddressBook.getContactName()).isEqualTo(UPDATED_CONTACT_NAME);
        assertThat(testAddressBook.getPhoneNo()).isEqualTo(UPDATED_PHONE_NO);
        assertThat(testAddressBook.getContactEmail()).isEqualTo(UPDATED_CONTACT_EMAIL);
        assertThat(testAddressBook.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testAddressBook.isNotify()).isEqualTo(UPDATED_NOTIFY);
        assertThat(testAddressBook.isResidential()).isEqualTo(UPDATED_RESIDENTIAL);
        assertThat(testAddressBook.getCreatedByUser()).isEqualTo(UPDATED_CREATED_BY_USER);
        assertThat(testAddressBook.getInstruction()).isEqualTo(UPDATED_INSTRUCTION);
        assertThat(testAddressBook.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testAddressBook.getDateUpdated()).isEqualTo(UPDATED_DATE_UPDATED);
    }

    @Test
    @Transactional
    public void updateNonExistingAddressBook() throws Exception {
        int databaseSizeBeforeUpdate = addressBookRepository.findAll().size();

        // Create the AddressBook

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddressBookMockMvc.perform(put("/api/address-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressBook)))
            .andExpect(status().isBadRequest());

        // Validate the AddressBook in the database
        List<AddressBook> addressBookList = addressBookRepository.findAll();
        assertThat(addressBookList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAddressBook() throws Exception {
        // Initialize the database
        addressBookRepository.saveAndFlush(addressBook);

        int databaseSizeBeforeDelete = addressBookRepository.findAll().size();

        // Delete the addressBook
        restAddressBookMockMvc.perform(delete("/api/address-books/{id}", addressBook.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AddressBook> addressBookList = addressBookRepository.findAll();
        assertThat(addressBookList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddressBook.class);
        AddressBook addressBook1 = new AddressBook();
        addressBook1.setId(1L);
        AddressBook addressBook2 = new AddressBook();
        addressBook2.setId(addressBook1.getId());
        assertThat(addressBook1).isEqualTo(addressBook2);
        addressBook2.setId(2L);
        assertThat(addressBook1).isNotEqualTo(addressBook2);
        addressBook1.setId(null);
        assertThat(addressBook1).isNotEqualTo(addressBook2);
    }
}
