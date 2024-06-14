package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.AnnualInventoryDeclaration;
import com.mycompany.myapp.domain.ApiaryInformation;
import com.mycompany.myapp.domain.enumeration.AnnualInventoryDeclarationState;
import com.mycompany.myapp.repository.AnnualInventoryDeclarationRepository;
import com.mycompany.myapp.service.criteria.AnnualInventoryDeclarationCriteria;
import com.mycompany.myapp.service.dto.AnnualInventoryDeclarationDTO;
import com.mycompany.myapp.service.mapper.AnnualInventoryDeclarationMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AnnualInventoryDeclarationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnnualInventoryDeclarationResourceIT {

    private static final Integer DEFAULT_TOTAL = 1;
    private static final Integer UPDATED_TOTAL = 2;
    private static final Integer SMALLER_TOTAL = 1 - 1;

    private static final Integer DEFAULT_BEEKEEPER_FAX_NUMBER = 1;
    private static final Integer UPDATED_BEEKEEPER_FAX_NUMBER = 2;
    private static final Integer SMALLER_BEEKEEPER_FAX_NUMBER = 1 - 1;

    private static final String DEFAULT_BEEKEEPER_COMPLETE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BEEKEEPER_COMPLETE_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_BEEKEEPER_NIF = 1;
    private static final Integer UPDATED_BEEKEEPER_NIF = 2;
    private static final Integer SMALLER_BEEKEEPER_NIF = 1 - 1;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_BEEKEEPER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_BEEKEEPER_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_BEEKEEPER_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BEEKEEPER_POSTAL_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_BEEKEEPER_PHONE_NUMBER = 1;
    private static final Integer UPDATED_BEEKEEPER_PHONE_NUMBER = 2;
    private static final Integer SMALLER_BEEKEEPER_PHONE_NUMBER = 1 - 1;

    private static final String DEFAULT_BEEKEEPER_ENTITY_ZONE_RESIDENCE = "AAAAAAAAAA";
    private static final String UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE = "BBBBBBBBBB";

    private static final Integer DEFAULT_BEEKEEPER_NUMBER = 1;
    private static final Integer UPDATED_BEEKEEPER_NUMBER = 2;
    private static final Integer SMALLER_BEEKEEPER_NUMBER = 1 - 1;

    private static final AnnualInventoryDeclarationState DEFAULT_ANNUAL_INVENTORY_DECLARATION_STATE =
        AnnualInventoryDeclarationState.APPROVED;
    private static final AnnualInventoryDeclarationState UPDATED_ANNUAL_INVENTORY_DECLARATION_STATE =
        AnnualInventoryDeclarationState.DECLINED;

    private static final LocalDate DEFAULT_REVISION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REVISION_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_REVISION_DATE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_REVISION_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_REVISION_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_REVISOR_SIGNATURE = "AAAAAAAAAA";
    private static final String UPDATED_REVISOR_SIGNATURE = "BBBBBBBBBB";

    private static final String DEFAULT_REVISOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REVISOR_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/annual-inventory-declarations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnnualInventoryDeclarationRepository annualInventoryDeclarationRepository;

    @Autowired
    private AnnualInventoryDeclarationMapper annualInventoryDeclarationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnnualInventoryDeclarationMockMvc;

    private AnnualInventoryDeclaration annualInventoryDeclaration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnnualInventoryDeclaration createEntity(EntityManager em) {
        AnnualInventoryDeclaration annualInventoryDeclaration = new AnnualInventoryDeclaration()
            .total(DEFAULT_TOTAL)
            .beekeeperFaxNumber(DEFAULT_BEEKEEPER_FAX_NUMBER)
            .beekeeperCompleteName(DEFAULT_BEEKEEPER_COMPLETE_NAME)
            .beekeeperNif(DEFAULT_BEEKEEPER_NIF)
            .date(DEFAULT_DATE)
            .beekeeperAddress(DEFAULT_BEEKEEPER_ADDRESS)
            .beekeeperPostalCode(DEFAULT_BEEKEEPER_POSTAL_CODE)
            .beekeeperPhoneNumber(DEFAULT_BEEKEEPER_PHONE_NUMBER)
            .beekeeperEntityZoneResidence(DEFAULT_BEEKEEPER_ENTITY_ZONE_RESIDENCE)
            .beekeeperNumber(DEFAULT_BEEKEEPER_NUMBER)
            .annualInventoryDeclarationState(DEFAULT_ANNUAL_INVENTORY_DECLARATION_STATE)
            .revisionDate(DEFAULT_REVISION_DATE)
            .revisionLocation(DEFAULT_REVISION_LOCATION)
            .revisorSignature(DEFAULT_REVISOR_SIGNATURE)
            .revisorName(DEFAULT_REVISOR_NAME);
        return annualInventoryDeclaration;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnnualInventoryDeclaration createUpdatedEntity(EntityManager em) {
        AnnualInventoryDeclaration annualInventoryDeclaration = new AnnualInventoryDeclaration()
            .total(UPDATED_TOTAL)
            .beekeeperFaxNumber(UPDATED_BEEKEEPER_FAX_NUMBER)
            .beekeeperCompleteName(UPDATED_BEEKEEPER_COMPLETE_NAME)
            .beekeeperNif(UPDATED_BEEKEEPER_NIF)
            .date(UPDATED_DATE)
            .beekeeperAddress(UPDATED_BEEKEEPER_ADDRESS)
            .beekeeperPostalCode(UPDATED_BEEKEEPER_POSTAL_CODE)
            .beekeeperPhoneNumber(UPDATED_BEEKEEPER_PHONE_NUMBER)
            .beekeeperEntityZoneResidence(UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE)
            .beekeeperNumber(UPDATED_BEEKEEPER_NUMBER)
            .annualInventoryDeclarationState(UPDATED_ANNUAL_INVENTORY_DECLARATION_STATE)
            .revisionDate(UPDATED_REVISION_DATE)
            .revisionLocation(UPDATED_REVISION_LOCATION)
            .revisorSignature(UPDATED_REVISOR_SIGNATURE)
            .revisorName(UPDATED_REVISOR_NAME);
        return annualInventoryDeclaration;
    }

    @BeforeEach
    public void initTest() {
        annualInventoryDeclaration = createEntity(em);
    }

    @Test
    @Transactional
    void createAnnualInventoryDeclarationWithExistingId() throws Exception {
        // Create the AnnualInventoryDeclaration with an existing ID
        annualInventoryDeclaration.setId(1L);
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        int databaseSizeBeforeCreate = annualInventoryDeclarationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnnualInventoryDeclarationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnnualInventoryDeclaration in the database
        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = annualInventoryDeclarationRepository.findAll().size();
        // set the field null
        annualInventoryDeclaration.setTotal(null);

        // Create the AnnualInventoryDeclaration, which fails.
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        restAnnualInventoryDeclarationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBeekeeperFaxNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = annualInventoryDeclarationRepository.findAll().size();
        // set the field null
        annualInventoryDeclaration.setBeekeeperFaxNumber(null);

        // Create the AnnualInventoryDeclaration, which fails.
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        restAnnualInventoryDeclarationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBeekeeperCompleteNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = annualInventoryDeclarationRepository.findAll().size();
        // set the field null
        annualInventoryDeclaration.setBeekeeperCompleteName(null);

        // Create the AnnualInventoryDeclaration, which fails.
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        restAnnualInventoryDeclarationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBeekeeperNifIsRequired() throws Exception {
        int databaseSizeBeforeTest = annualInventoryDeclarationRepository.findAll().size();
        // set the field null
        annualInventoryDeclaration.setBeekeeperNif(null);

        // Create the AnnualInventoryDeclaration, which fails.
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        restAnnualInventoryDeclarationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = annualInventoryDeclarationRepository.findAll().size();
        // set the field null
        annualInventoryDeclaration.setDate(null);

        // Create the AnnualInventoryDeclaration, which fails.
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        restAnnualInventoryDeclarationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBeekeeperAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = annualInventoryDeclarationRepository.findAll().size();
        // set the field null
        annualInventoryDeclaration.setBeekeeperAddress(null);

        // Create the AnnualInventoryDeclaration, which fails.
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        restAnnualInventoryDeclarationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBeekeeperPostalCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = annualInventoryDeclarationRepository.findAll().size();
        // set the field null
        annualInventoryDeclaration.setBeekeeperPostalCode(null);

        // Create the AnnualInventoryDeclaration, which fails.
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        restAnnualInventoryDeclarationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBeekeeperPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = annualInventoryDeclarationRepository.findAll().size();
        // set the field null
        annualInventoryDeclaration.setBeekeeperPhoneNumber(null);

        // Create the AnnualInventoryDeclaration, which fails.
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        restAnnualInventoryDeclarationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBeekeeperEntityZoneResidenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = annualInventoryDeclarationRepository.findAll().size();
        // set the field null
        annualInventoryDeclaration.setBeekeeperEntityZoneResidence(null);

        // Create the AnnualInventoryDeclaration, which fails.
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        restAnnualInventoryDeclarationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBeekeeperNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = annualInventoryDeclarationRepository.findAll().size();
        // set the field null
        annualInventoryDeclaration.setBeekeeperNumber(null);

        // Create the AnnualInventoryDeclaration, which fails.
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        restAnnualInventoryDeclarationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarations() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList
        restAnnualInventoryDeclarationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(annualInventoryDeclaration.getId().intValue())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL)))
            .andExpect(jsonPath("$.[*].beekeeperFaxNumber").value(hasItem(DEFAULT_BEEKEEPER_FAX_NUMBER)))
            .andExpect(jsonPath("$.[*].beekeeperCompleteName").value(hasItem(DEFAULT_BEEKEEPER_COMPLETE_NAME)))
            .andExpect(jsonPath("$.[*].beekeeperNif").value(hasItem(DEFAULT_BEEKEEPER_NIF)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].beekeeperAddress").value(hasItem(DEFAULT_BEEKEEPER_ADDRESS)))
            .andExpect(jsonPath("$.[*].beekeeperPostalCode").value(hasItem(DEFAULT_BEEKEEPER_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].beekeeperPhoneNumber").value(hasItem(DEFAULT_BEEKEEPER_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].beekeeperEntityZoneResidence").value(hasItem(DEFAULT_BEEKEEPER_ENTITY_ZONE_RESIDENCE)))
            .andExpect(jsonPath("$.[*].beekeeperNumber").value(hasItem(DEFAULT_BEEKEEPER_NUMBER)))
            .andExpect(
                jsonPath("$.[*].annualInventoryDeclarationState").value(hasItem(DEFAULT_ANNUAL_INVENTORY_DECLARATION_STATE.toString()))
            )
            .andExpect(jsonPath("$.[*].revisionDate").value(hasItem(DEFAULT_REVISION_DATE.toString())))
            .andExpect(jsonPath("$.[*].revisionLocation").value(hasItem(DEFAULT_REVISION_LOCATION)))
            .andExpect(jsonPath("$.[*].revisorSignature").value(hasItem(DEFAULT_REVISOR_SIGNATURE)))
            .andExpect(jsonPath("$.[*].revisorName").value(hasItem(DEFAULT_REVISOR_NAME)));
    }

    @Test
    @Transactional
    void getAnnualInventoryDeclaration() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get the annualInventoryDeclaration
        restAnnualInventoryDeclarationMockMvc
            .perform(get(ENTITY_API_URL_ID, annualInventoryDeclaration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(annualInventoryDeclaration.getId().intValue()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL))
            .andExpect(jsonPath("$.beekeeperFaxNumber").value(DEFAULT_BEEKEEPER_FAX_NUMBER))
            .andExpect(jsonPath("$.beekeeperCompleteName").value(DEFAULT_BEEKEEPER_COMPLETE_NAME))
            .andExpect(jsonPath("$.beekeeperNif").value(DEFAULT_BEEKEEPER_NIF))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.beekeeperAddress").value(DEFAULT_BEEKEEPER_ADDRESS))
            .andExpect(jsonPath("$.beekeeperPostalCode").value(DEFAULT_BEEKEEPER_POSTAL_CODE))
            .andExpect(jsonPath("$.beekeeperPhoneNumber").value(DEFAULT_BEEKEEPER_PHONE_NUMBER))
            .andExpect(jsonPath("$.beekeeperEntityZoneResidence").value(DEFAULT_BEEKEEPER_ENTITY_ZONE_RESIDENCE))
            .andExpect(jsonPath("$.beekeeperNumber").value(DEFAULT_BEEKEEPER_NUMBER))
            .andExpect(jsonPath("$.annualInventoryDeclarationState").value(DEFAULT_ANNUAL_INVENTORY_DECLARATION_STATE.toString()))
            .andExpect(jsonPath("$.revisionDate").value(DEFAULT_REVISION_DATE.toString()))
            .andExpect(jsonPath("$.revisionLocation").value(DEFAULT_REVISION_LOCATION))
            .andExpect(jsonPath("$.revisorSignature").value(DEFAULT_REVISOR_SIGNATURE))
            .andExpect(jsonPath("$.revisorName").value(DEFAULT_REVISOR_NAME));
    }

    @Test
    @Transactional
    void getAnnualInventoryDeclarationsByIdFiltering() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        Long id = annualInventoryDeclaration.getId();

        defaultAnnualInventoryDeclarationShouldBeFound("id.equals=" + id);
        defaultAnnualInventoryDeclarationShouldNotBeFound("id.notEquals=" + id);

        defaultAnnualInventoryDeclarationShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultAnnualInventoryDeclarationShouldNotBeFound("id.greaterThan=" + id);

        defaultAnnualInventoryDeclarationShouldBeFound("id.lessThanOrEqual=" + id);
        defaultAnnualInventoryDeclarationShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByTotalIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where total equals to DEFAULT_TOTAL
        defaultAnnualInventoryDeclarationShouldBeFound("total.equals=" + DEFAULT_TOTAL);

        // Get all the annualInventoryDeclarationList where total equals to UPDATED_TOTAL
        defaultAnnualInventoryDeclarationShouldNotBeFound("total.equals=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByTotalIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where total in DEFAULT_TOTAL or UPDATED_TOTAL
        defaultAnnualInventoryDeclarationShouldBeFound("total.in=" + DEFAULT_TOTAL + "," + UPDATED_TOTAL);

        // Get all the annualInventoryDeclarationList where total equals to UPDATED_TOTAL
        defaultAnnualInventoryDeclarationShouldNotBeFound("total.in=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByTotalIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where total is not null
        defaultAnnualInventoryDeclarationShouldBeFound("total.specified=true");

        // Get all the annualInventoryDeclarationList where total is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("total.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByTotalIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where total is greater than or equal to DEFAULT_TOTAL
        defaultAnnualInventoryDeclarationShouldBeFound("total.greaterThanOrEqual=" + DEFAULT_TOTAL);

        // Get all the annualInventoryDeclarationList where total is greater than or equal to UPDATED_TOTAL
        defaultAnnualInventoryDeclarationShouldNotBeFound("total.greaterThanOrEqual=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByTotalIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where total is less than or equal to DEFAULT_TOTAL
        defaultAnnualInventoryDeclarationShouldBeFound("total.lessThanOrEqual=" + DEFAULT_TOTAL);

        // Get all the annualInventoryDeclarationList where total is less than or equal to SMALLER_TOTAL
        defaultAnnualInventoryDeclarationShouldNotBeFound("total.lessThanOrEqual=" + SMALLER_TOTAL);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByTotalIsLessThanSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where total is less than DEFAULT_TOTAL
        defaultAnnualInventoryDeclarationShouldNotBeFound("total.lessThan=" + DEFAULT_TOTAL);

        // Get all the annualInventoryDeclarationList where total is less than UPDATED_TOTAL
        defaultAnnualInventoryDeclarationShouldBeFound("total.lessThan=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByTotalIsGreaterThanSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where total is greater than DEFAULT_TOTAL
        defaultAnnualInventoryDeclarationShouldNotBeFound("total.greaterThan=" + DEFAULT_TOTAL);

        // Get all the annualInventoryDeclarationList where total is greater than SMALLER_TOTAL
        defaultAnnualInventoryDeclarationShouldBeFound("total.greaterThan=" + SMALLER_TOTAL);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperFaxNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperFaxNumber equals to DEFAULT_BEEKEEPER_FAX_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperFaxNumber.equals=" + DEFAULT_BEEKEEPER_FAX_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperFaxNumber equals to UPDATED_BEEKEEPER_FAX_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperFaxNumber.equals=" + UPDATED_BEEKEEPER_FAX_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperFaxNumberIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperFaxNumber in DEFAULT_BEEKEEPER_FAX_NUMBER or UPDATED_BEEKEEPER_FAX_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound(
            "beekeeperFaxNumber.in=" + DEFAULT_BEEKEEPER_FAX_NUMBER + "," + UPDATED_BEEKEEPER_FAX_NUMBER
        );

        // Get all the annualInventoryDeclarationList where beekeeperFaxNumber equals to UPDATED_BEEKEEPER_FAX_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperFaxNumber.in=" + UPDATED_BEEKEEPER_FAX_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperFaxNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperFaxNumber is not null
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperFaxNumber.specified=true");

        // Get all the annualInventoryDeclarationList where beekeeperFaxNumber is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperFaxNumber.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperFaxNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperFaxNumber is greater than or equal to DEFAULT_BEEKEEPER_FAX_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperFaxNumber.greaterThanOrEqual=" + DEFAULT_BEEKEEPER_FAX_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperFaxNumber is greater than or equal to UPDATED_BEEKEEPER_FAX_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperFaxNumber.greaterThanOrEqual=" + UPDATED_BEEKEEPER_FAX_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperFaxNumberIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperFaxNumber is less than or equal to DEFAULT_BEEKEEPER_FAX_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperFaxNumber.lessThanOrEqual=" + DEFAULT_BEEKEEPER_FAX_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperFaxNumber is less than or equal to SMALLER_BEEKEEPER_FAX_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperFaxNumber.lessThanOrEqual=" + SMALLER_BEEKEEPER_FAX_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperFaxNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperFaxNumber is less than DEFAULT_BEEKEEPER_FAX_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperFaxNumber.lessThan=" + DEFAULT_BEEKEEPER_FAX_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperFaxNumber is less than UPDATED_BEEKEEPER_FAX_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperFaxNumber.lessThan=" + UPDATED_BEEKEEPER_FAX_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperFaxNumberIsGreaterThanSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperFaxNumber is greater than DEFAULT_BEEKEEPER_FAX_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperFaxNumber.greaterThan=" + DEFAULT_BEEKEEPER_FAX_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperFaxNumber is greater than SMALLER_BEEKEEPER_FAX_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperFaxNumber.greaterThan=" + SMALLER_BEEKEEPER_FAX_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperCompleteNameIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperCompleteName equals to DEFAULT_BEEKEEPER_COMPLETE_NAME
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperCompleteName.equals=" + DEFAULT_BEEKEEPER_COMPLETE_NAME);

        // Get all the annualInventoryDeclarationList where beekeeperCompleteName equals to UPDATED_BEEKEEPER_COMPLETE_NAME
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperCompleteName.equals=" + UPDATED_BEEKEEPER_COMPLETE_NAME);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperCompleteNameIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperCompleteName in DEFAULT_BEEKEEPER_COMPLETE_NAME or UPDATED_BEEKEEPER_COMPLETE_NAME
        defaultAnnualInventoryDeclarationShouldBeFound(
            "beekeeperCompleteName.in=" + DEFAULT_BEEKEEPER_COMPLETE_NAME + "," + UPDATED_BEEKEEPER_COMPLETE_NAME
        );

        // Get all the annualInventoryDeclarationList where beekeeperCompleteName equals to UPDATED_BEEKEEPER_COMPLETE_NAME
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperCompleteName.in=" + UPDATED_BEEKEEPER_COMPLETE_NAME);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperCompleteNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperCompleteName is not null
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperCompleteName.specified=true");

        // Get all the annualInventoryDeclarationList where beekeeperCompleteName is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperCompleteName.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperCompleteNameContainsSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperCompleteName contains DEFAULT_BEEKEEPER_COMPLETE_NAME
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperCompleteName.contains=" + DEFAULT_BEEKEEPER_COMPLETE_NAME);

        // Get all the annualInventoryDeclarationList where beekeeperCompleteName contains UPDATED_BEEKEEPER_COMPLETE_NAME
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperCompleteName.contains=" + UPDATED_BEEKEEPER_COMPLETE_NAME);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperCompleteNameNotContainsSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperCompleteName does not contain DEFAULT_BEEKEEPER_COMPLETE_NAME
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperCompleteName.doesNotContain=" + DEFAULT_BEEKEEPER_COMPLETE_NAME);

        // Get all the annualInventoryDeclarationList where beekeeperCompleteName does not contain UPDATED_BEEKEEPER_COMPLETE_NAME
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperCompleteName.doesNotContain=" + UPDATED_BEEKEEPER_COMPLETE_NAME);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperNifIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperNif equals to DEFAULT_BEEKEEPER_NIF
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperNif.equals=" + DEFAULT_BEEKEEPER_NIF);

        // Get all the annualInventoryDeclarationList where beekeeperNif equals to UPDATED_BEEKEEPER_NIF
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperNif.equals=" + UPDATED_BEEKEEPER_NIF);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperNifIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperNif in DEFAULT_BEEKEEPER_NIF or UPDATED_BEEKEEPER_NIF
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperNif.in=" + DEFAULT_BEEKEEPER_NIF + "," + UPDATED_BEEKEEPER_NIF);

        // Get all the annualInventoryDeclarationList where beekeeperNif equals to UPDATED_BEEKEEPER_NIF
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperNif.in=" + UPDATED_BEEKEEPER_NIF);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperNifIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperNif is not null
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperNif.specified=true");

        // Get all the annualInventoryDeclarationList where beekeeperNif is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperNif.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperNifIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperNif is greater than or equal to DEFAULT_BEEKEEPER_NIF
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperNif.greaterThanOrEqual=" + DEFAULT_BEEKEEPER_NIF);

        // Get all the annualInventoryDeclarationList where beekeeperNif is greater than or equal to UPDATED_BEEKEEPER_NIF
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperNif.greaterThanOrEqual=" + UPDATED_BEEKEEPER_NIF);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperNifIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperNif is less than or equal to DEFAULT_BEEKEEPER_NIF
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperNif.lessThanOrEqual=" + DEFAULT_BEEKEEPER_NIF);

        // Get all the annualInventoryDeclarationList where beekeeperNif is less than or equal to SMALLER_BEEKEEPER_NIF
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperNif.lessThanOrEqual=" + SMALLER_BEEKEEPER_NIF);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperNifIsLessThanSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperNif is less than DEFAULT_BEEKEEPER_NIF
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperNif.lessThan=" + DEFAULT_BEEKEEPER_NIF);

        // Get all the annualInventoryDeclarationList where beekeeperNif is less than UPDATED_BEEKEEPER_NIF
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperNif.lessThan=" + UPDATED_BEEKEEPER_NIF);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperNifIsGreaterThanSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperNif is greater than DEFAULT_BEEKEEPER_NIF
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperNif.greaterThan=" + DEFAULT_BEEKEEPER_NIF);

        // Get all the annualInventoryDeclarationList where beekeeperNif is greater than SMALLER_BEEKEEPER_NIF
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperNif.greaterThan=" + SMALLER_BEEKEEPER_NIF);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where date equals to DEFAULT_DATE
        defaultAnnualInventoryDeclarationShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the annualInventoryDeclarationList where date equals to UPDATED_DATE
        defaultAnnualInventoryDeclarationShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByDateIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where date in DEFAULT_DATE or UPDATED_DATE
        defaultAnnualInventoryDeclarationShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the annualInventoryDeclarationList where date equals to UPDATED_DATE
        defaultAnnualInventoryDeclarationShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where date is not null
        defaultAnnualInventoryDeclarationShouldBeFound("date.specified=true");

        // Get all the annualInventoryDeclarationList where date is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where date is greater than or equal to DEFAULT_DATE
        defaultAnnualInventoryDeclarationShouldBeFound("date.greaterThanOrEqual=" + DEFAULT_DATE);

        // Get all the annualInventoryDeclarationList where date is greater than or equal to UPDATED_DATE
        defaultAnnualInventoryDeclarationShouldNotBeFound("date.greaterThanOrEqual=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where date is less than or equal to DEFAULT_DATE
        defaultAnnualInventoryDeclarationShouldBeFound("date.lessThanOrEqual=" + DEFAULT_DATE);

        // Get all the annualInventoryDeclarationList where date is less than or equal to SMALLER_DATE
        defaultAnnualInventoryDeclarationShouldNotBeFound("date.lessThanOrEqual=" + SMALLER_DATE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByDateIsLessThanSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where date is less than DEFAULT_DATE
        defaultAnnualInventoryDeclarationShouldNotBeFound("date.lessThan=" + DEFAULT_DATE);

        // Get all the annualInventoryDeclarationList where date is less than UPDATED_DATE
        defaultAnnualInventoryDeclarationShouldBeFound("date.lessThan=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where date is greater than DEFAULT_DATE
        defaultAnnualInventoryDeclarationShouldNotBeFound("date.greaterThan=" + DEFAULT_DATE);

        // Get all the annualInventoryDeclarationList where date is greater than SMALLER_DATE
        defaultAnnualInventoryDeclarationShouldBeFound("date.greaterThan=" + SMALLER_DATE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperAddress equals to DEFAULT_BEEKEEPER_ADDRESS
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperAddress.equals=" + DEFAULT_BEEKEEPER_ADDRESS);

        // Get all the annualInventoryDeclarationList where beekeeperAddress equals to UPDATED_BEEKEEPER_ADDRESS
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperAddress.equals=" + UPDATED_BEEKEEPER_ADDRESS);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperAddressIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperAddress in DEFAULT_BEEKEEPER_ADDRESS or UPDATED_BEEKEEPER_ADDRESS
        defaultAnnualInventoryDeclarationShouldBeFound(
            "beekeeperAddress.in=" + DEFAULT_BEEKEEPER_ADDRESS + "," + UPDATED_BEEKEEPER_ADDRESS
        );

        // Get all the annualInventoryDeclarationList where beekeeperAddress equals to UPDATED_BEEKEEPER_ADDRESS
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperAddress.in=" + UPDATED_BEEKEEPER_ADDRESS);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperAddress is not null
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperAddress.specified=true");

        // Get all the annualInventoryDeclarationList where beekeeperAddress is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperAddress.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperAddressContainsSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperAddress contains DEFAULT_BEEKEEPER_ADDRESS
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperAddress.contains=" + DEFAULT_BEEKEEPER_ADDRESS);

        // Get all the annualInventoryDeclarationList where beekeeperAddress contains UPDATED_BEEKEEPER_ADDRESS
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperAddress.contains=" + UPDATED_BEEKEEPER_ADDRESS);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperAddressNotContainsSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperAddress does not contain DEFAULT_BEEKEEPER_ADDRESS
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperAddress.doesNotContain=" + DEFAULT_BEEKEEPER_ADDRESS);

        // Get all the annualInventoryDeclarationList where beekeeperAddress does not contain UPDATED_BEEKEEPER_ADDRESS
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperAddress.doesNotContain=" + UPDATED_BEEKEEPER_ADDRESS);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperPostalCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperPostalCode equals to DEFAULT_BEEKEEPER_POSTAL_CODE
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperPostalCode.equals=" + DEFAULT_BEEKEEPER_POSTAL_CODE);

        // Get all the annualInventoryDeclarationList where beekeeperPostalCode equals to UPDATED_BEEKEEPER_POSTAL_CODE
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperPostalCode.equals=" + UPDATED_BEEKEEPER_POSTAL_CODE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperPostalCodeIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperPostalCode in DEFAULT_BEEKEEPER_POSTAL_CODE or UPDATED_BEEKEEPER_POSTAL_CODE
        defaultAnnualInventoryDeclarationShouldBeFound(
            "beekeeperPostalCode.in=" + DEFAULT_BEEKEEPER_POSTAL_CODE + "," + UPDATED_BEEKEEPER_POSTAL_CODE
        );

        // Get all the annualInventoryDeclarationList where beekeeperPostalCode equals to UPDATED_BEEKEEPER_POSTAL_CODE
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperPostalCode.in=" + UPDATED_BEEKEEPER_POSTAL_CODE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperPostalCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperPostalCode is not null
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperPostalCode.specified=true");

        // Get all the annualInventoryDeclarationList where beekeeperPostalCode is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperPostalCode.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperPostalCodeContainsSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperPostalCode contains DEFAULT_BEEKEEPER_POSTAL_CODE
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperPostalCode.contains=" + DEFAULT_BEEKEEPER_POSTAL_CODE);

        // Get all the annualInventoryDeclarationList where beekeeperPostalCode contains UPDATED_BEEKEEPER_POSTAL_CODE
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperPostalCode.contains=" + UPDATED_BEEKEEPER_POSTAL_CODE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperPostalCodeNotContainsSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperPostalCode does not contain DEFAULT_BEEKEEPER_POSTAL_CODE
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperPostalCode.doesNotContain=" + DEFAULT_BEEKEEPER_POSTAL_CODE);

        // Get all the annualInventoryDeclarationList where beekeeperPostalCode does not contain UPDATED_BEEKEEPER_POSTAL_CODE
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperPostalCode.doesNotContain=" + UPDATED_BEEKEEPER_POSTAL_CODE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperPhoneNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperPhoneNumber equals to DEFAULT_BEEKEEPER_PHONE_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperPhoneNumber.equals=" + DEFAULT_BEEKEEPER_PHONE_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperPhoneNumber equals to UPDATED_BEEKEEPER_PHONE_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperPhoneNumber.equals=" + UPDATED_BEEKEEPER_PHONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperPhoneNumberIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperPhoneNumber in DEFAULT_BEEKEEPER_PHONE_NUMBER or UPDATED_BEEKEEPER_PHONE_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound(
            "beekeeperPhoneNumber.in=" + DEFAULT_BEEKEEPER_PHONE_NUMBER + "," + UPDATED_BEEKEEPER_PHONE_NUMBER
        );

        // Get all the annualInventoryDeclarationList where beekeeperPhoneNumber equals to UPDATED_BEEKEEPER_PHONE_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperPhoneNumber.in=" + UPDATED_BEEKEEPER_PHONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperPhoneNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperPhoneNumber is not null
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperPhoneNumber.specified=true");

        // Get all the annualInventoryDeclarationList where beekeeperPhoneNumber is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperPhoneNumber.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperPhoneNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperPhoneNumber is greater than or equal to DEFAULT_BEEKEEPER_PHONE_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperPhoneNumber.greaterThanOrEqual=" + DEFAULT_BEEKEEPER_PHONE_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperPhoneNumber is greater than or equal to UPDATED_BEEKEEPER_PHONE_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperPhoneNumber.greaterThanOrEqual=" + UPDATED_BEEKEEPER_PHONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperPhoneNumberIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperPhoneNumber is less than or equal to DEFAULT_BEEKEEPER_PHONE_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperPhoneNumber.lessThanOrEqual=" + DEFAULT_BEEKEEPER_PHONE_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperPhoneNumber is less than or equal to SMALLER_BEEKEEPER_PHONE_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperPhoneNumber.lessThanOrEqual=" + SMALLER_BEEKEEPER_PHONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperPhoneNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperPhoneNumber is less than DEFAULT_BEEKEEPER_PHONE_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperPhoneNumber.lessThan=" + DEFAULT_BEEKEEPER_PHONE_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperPhoneNumber is less than UPDATED_BEEKEEPER_PHONE_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperPhoneNumber.lessThan=" + UPDATED_BEEKEEPER_PHONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperPhoneNumberIsGreaterThanSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperPhoneNumber is greater than DEFAULT_BEEKEEPER_PHONE_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperPhoneNumber.greaterThan=" + DEFAULT_BEEKEEPER_PHONE_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperPhoneNumber is greater than SMALLER_BEEKEEPER_PHONE_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperPhoneNumber.greaterThan=" + SMALLER_BEEKEEPER_PHONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperEntityZoneResidenceIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperEntityZoneResidence equals to DEFAULT_BEEKEEPER_ENTITY_ZONE_RESIDENCE
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperEntityZoneResidence.equals=" + DEFAULT_BEEKEEPER_ENTITY_ZONE_RESIDENCE);

        // Get all the annualInventoryDeclarationList where beekeeperEntityZoneResidence equals to UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperEntityZoneResidence.equals=" + UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperEntityZoneResidenceIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperEntityZoneResidence in DEFAULT_BEEKEEPER_ENTITY_ZONE_RESIDENCE or UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE
        defaultAnnualInventoryDeclarationShouldBeFound(
            "beekeeperEntityZoneResidence.in=" + DEFAULT_BEEKEEPER_ENTITY_ZONE_RESIDENCE + "," + UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE
        );

        // Get all the annualInventoryDeclarationList where beekeeperEntityZoneResidence equals to UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperEntityZoneResidence.in=" + UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperEntityZoneResidenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperEntityZoneResidence is not null
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperEntityZoneResidence.specified=true");

        // Get all the annualInventoryDeclarationList where beekeeperEntityZoneResidence is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperEntityZoneResidence.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperEntityZoneResidenceContainsSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperEntityZoneResidence contains DEFAULT_BEEKEEPER_ENTITY_ZONE_RESIDENCE
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperEntityZoneResidence.contains=" + DEFAULT_BEEKEEPER_ENTITY_ZONE_RESIDENCE);

        // Get all the annualInventoryDeclarationList where beekeeperEntityZoneResidence contains UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE
        defaultAnnualInventoryDeclarationShouldNotBeFound(
            "beekeeperEntityZoneResidence.contains=" + UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE
        );
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperEntityZoneResidenceNotContainsSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperEntityZoneResidence does not contain DEFAULT_BEEKEEPER_ENTITY_ZONE_RESIDENCE
        defaultAnnualInventoryDeclarationShouldNotBeFound(
            "beekeeperEntityZoneResidence.doesNotContain=" + DEFAULT_BEEKEEPER_ENTITY_ZONE_RESIDENCE
        );

        // Get all the annualInventoryDeclarationList where beekeeperEntityZoneResidence does not contain UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE
        defaultAnnualInventoryDeclarationShouldBeFound(
            "beekeeperEntityZoneResidence.doesNotContain=" + UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE
        );
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperNumber equals to DEFAULT_BEEKEEPER_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperNumber.equals=" + DEFAULT_BEEKEEPER_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperNumber equals to UPDATED_BEEKEEPER_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperNumber.equals=" + UPDATED_BEEKEEPER_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperNumberIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperNumber in DEFAULT_BEEKEEPER_NUMBER or UPDATED_BEEKEEPER_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperNumber.in=" + DEFAULT_BEEKEEPER_NUMBER + "," + UPDATED_BEEKEEPER_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperNumber equals to UPDATED_BEEKEEPER_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperNumber.in=" + UPDATED_BEEKEEPER_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperNumber is not null
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperNumber.specified=true");

        // Get all the annualInventoryDeclarationList where beekeeperNumber is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperNumber.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperNumber is greater than or equal to DEFAULT_BEEKEEPER_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperNumber.greaterThanOrEqual=" + DEFAULT_BEEKEEPER_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperNumber is greater than or equal to UPDATED_BEEKEEPER_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperNumber.greaterThanOrEqual=" + UPDATED_BEEKEEPER_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperNumberIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperNumber is less than or equal to DEFAULT_BEEKEEPER_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperNumber.lessThanOrEqual=" + DEFAULT_BEEKEEPER_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperNumber is less than or equal to SMALLER_BEEKEEPER_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperNumber.lessThanOrEqual=" + SMALLER_BEEKEEPER_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperNumber is less than DEFAULT_BEEKEEPER_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperNumber.lessThan=" + DEFAULT_BEEKEEPER_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperNumber is less than UPDATED_BEEKEEPER_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperNumber.lessThan=" + UPDATED_BEEKEEPER_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByBeekeeperNumberIsGreaterThanSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where beekeeperNumber is greater than DEFAULT_BEEKEEPER_NUMBER
        defaultAnnualInventoryDeclarationShouldNotBeFound("beekeeperNumber.greaterThan=" + DEFAULT_BEEKEEPER_NUMBER);

        // Get all the annualInventoryDeclarationList where beekeeperNumber is greater than SMALLER_BEEKEEPER_NUMBER
        defaultAnnualInventoryDeclarationShouldBeFound("beekeeperNumber.greaterThan=" + SMALLER_BEEKEEPER_NUMBER);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByAnnualInventoryDeclarationStateIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where annualInventoryDeclarationState equals to DEFAULT_ANNUAL_INVENTORY_DECLARATION_STATE
        defaultAnnualInventoryDeclarationShouldBeFound(
            "annualInventoryDeclarationState.equals=" + DEFAULT_ANNUAL_INVENTORY_DECLARATION_STATE
        );

        // Get all the annualInventoryDeclarationList where annualInventoryDeclarationState equals to UPDATED_ANNUAL_INVENTORY_DECLARATION_STATE
        defaultAnnualInventoryDeclarationShouldNotBeFound(
            "annualInventoryDeclarationState.equals=" + UPDATED_ANNUAL_INVENTORY_DECLARATION_STATE
        );
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByAnnualInventoryDeclarationStateIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where annualInventoryDeclarationState in DEFAULT_ANNUAL_INVENTORY_DECLARATION_STATE or UPDATED_ANNUAL_INVENTORY_DECLARATION_STATE
        defaultAnnualInventoryDeclarationShouldBeFound(
            "annualInventoryDeclarationState.in=" +
            DEFAULT_ANNUAL_INVENTORY_DECLARATION_STATE +
            "," +
            UPDATED_ANNUAL_INVENTORY_DECLARATION_STATE
        );

        // Get all the annualInventoryDeclarationList where annualInventoryDeclarationState equals to UPDATED_ANNUAL_INVENTORY_DECLARATION_STATE
        defaultAnnualInventoryDeclarationShouldNotBeFound(
            "annualInventoryDeclarationState.in=" + UPDATED_ANNUAL_INVENTORY_DECLARATION_STATE
        );
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByAnnualInventoryDeclarationStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where annualInventoryDeclarationState is not null
        defaultAnnualInventoryDeclarationShouldBeFound("annualInventoryDeclarationState.specified=true");

        // Get all the annualInventoryDeclarationList where annualInventoryDeclarationState is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("annualInventoryDeclarationState.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisionDateIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisionDate equals to DEFAULT_REVISION_DATE
        defaultAnnualInventoryDeclarationShouldBeFound("revisionDate.equals=" + DEFAULT_REVISION_DATE);

        // Get all the annualInventoryDeclarationList where revisionDate equals to UPDATED_REVISION_DATE
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisionDate.equals=" + UPDATED_REVISION_DATE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisionDateIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisionDate in DEFAULT_REVISION_DATE or UPDATED_REVISION_DATE
        defaultAnnualInventoryDeclarationShouldBeFound("revisionDate.in=" + DEFAULT_REVISION_DATE + "," + UPDATED_REVISION_DATE);

        // Get all the annualInventoryDeclarationList where revisionDate equals to UPDATED_REVISION_DATE
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisionDate.in=" + UPDATED_REVISION_DATE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisionDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisionDate is not null
        defaultAnnualInventoryDeclarationShouldBeFound("revisionDate.specified=true");

        // Get all the annualInventoryDeclarationList where revisionDate is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisionDate.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisionDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisionDate is greater than or equal to DEFAULT_REVISION_DATE
        defaultAnnualInventoryDeclarationShouldBeFound("revisionDate.greaterThanOrEqual=" + DEFAULT_REVISION_DATE);

        // Get all the annualInventoryDeclarationList where revisionDate is greater than or equal to UPDATED_REVISION_DATE
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisionDate.greaterThanOrEqual=" + UPDATED_REVISION_DATE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisionDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisionDate is less than or equal to DEFAULT_REVISION_DATE
        defaultAnnualInventoryDeclarationShouldBeFound("revisionDate.lessThanOrEqual=" + DEFAULT_REVISION_DATE);

        // Get all the annualInventoryDeclarationList where revisionDate is less than or equal to SMALLER_REVISION_DATE
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisionDate.lessThanOrEqual=" + SMALLER_REVISION_DATE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisionDateIsLessThanSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisionDate is less than DEFAULT_REVISION_DATE
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisionDate.lessThan=" + DEFAULT_REVISION_DATE);

        // Get all the annualInventoryDeclarationList where revisionDate is less than UPDATED_REVISION_DATE
        defaultAnnualInventoryDeclarationShouldBeFound("revisionDate.lessThan=" + UPDATED_REVISION_DATE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisionDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisionDate is greater than DEFAULT_REVISION_DATE
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisionDate.greaterThan=" + DEFAULT_REVISION_DATE);

        // Get all the annualInventoryDeclarationList where revisionDate is greater than SMALLER_REVISION_DATE
        defaultAnnualInventoryDeclarationShouldBeFound("revisionDate.greaterThan=" + SMALLER_REVISION_DATE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisionLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisionLocation equals to DEFAULT_REVISION_LOCATION
        defaultAnnualInventoryDeclarationShouldBeFound("revisionLocation.equals=" + DEFAULT_REVISION_LOCATION);

        // Get all the annualInventoryDeclarationList where revisionLocation equals to UPDATED_REVISION_LOCATION
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisionLocation.equals=" + UPDATED_REVISION_LOCATION);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisionLocationIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisionLocation in DEFAULT_REVISION_LOCATION or UPDATED_REVISION_LOCATION
        defaultAnnualInventoryDeclarationShouldBeFound(
            "revisionLocation.in=" + DEFAULT_REVISION_LOCATION + "," + UPDATED_REVISION_LOCATION
        );

        // Get all the annualInventoryDeclarationList where revisionLocation equals to UPDATED_REVISION_LOCATION
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisionLocation.in=" + UPDATED_REVISION_LOCATION);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisionLocationIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisionLocation is not null
        defaultAnnualInventoryDeclarationShouldBeFound("revisionLocation.specified=true");

        // Get all the annualInventoryDeclarationList where revisionLocation is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisionLocation.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisionLocationContainsSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisionLocation contains DEFAULT_REVISION_LOCATION
        defaultAnnualInventoryDeclarationShouldBeFound("revisionLocation.contains=" + DEFAULT_REVISION_LOCATION);

        // Get all the annualInventoryDeclarationList where revisionLocation contains UPDATED_REVISION_LOCATION
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisionLocation.contains=" + UPDATED_REVISION_LOCATION);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisionLocationNotContainsSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisionLocation does not contain DEFAULT_REVISION_LOCATION
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisionLocation.doesNotContain=" + DEFAULT_REVISION_LOCATION);

        // Get all the annualInventoryDeclarationList where revisionLocation does not contain UPDATED_REVISION_LOCATION
        defaultAnnualInventoryDeclarationShouldBeFound("revisionLocation.doesNotContain=" + UPDATED_REVISION_LOCATION);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisorSignatureIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisorSignature equals to DEFAULT_REVISOR_SIGNATURE
        defaultAnnualInventoryDeclarationShouldBeFound("revisorSignature.equals=" + DEFAULT_REVISOR_SIGNATURE);

        // Get all the annualInventoryDeclarationList where revisorSignature equals to UPDATED_REVISOR_SIGNATURE
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisorSignature.equals=" + UPDATED_REVISOR_SIGNATURE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisorSignatureIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisorSignature in DEFAULT_REVISOR_SIGNATURE or UPDATED_REVISOR_SIGNATURE
        defaultAnnualInventoryDeclarationShouldBeFound(
            "revisorSignature.in=" + DEFAULT_REVISOR_SIGNATURE + "," + UPDATED_REVISOR_SIGNATURE
        );

        // Get all the annualInventoryDeclarationList where revisorSignature equals to UPDATED_REVISOR_SIGNATURE
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisorSignature.in=" + UPDATED_REVISOR_SIGNATURE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisorSignatureIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisorSignature is not null
        defaultAnnualInventoryDeclarationShouldBeFound("revisorSignature.specified=true");

        // Get all the annualInventoryDeclarationList where revisorSignature is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisorSignature.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisorSignatureContainsSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisorSignature contains DEFAULT_REVISOR_SIGNATURE
        defaultAnnualInventoryDeclarationShouldBeFound("revisorSignature.contains=" + DEFAULT_REVISOR_SIGNATURE);

        // Get all the annualInventoryDeclarationList where revisorSignature contains UPDATED_REVISOR_SIGNATURE
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisorSignature.contains=" + UPDATED_REVISOR_SIGNATURE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisorSignatureNotContainsSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisorSignature does not contain DEFAULT_REVISOR_SIGNATURE
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisorSignature.doesNotContain=" + DEFAULT_REVISOR_SIGNATURE);

        // Get all the annualInventoryDeclarationList where revisorSignature does not contain UPDATED_REVISOR_SIGNATURE
        defaultAnnualInventoryDeclarationShouldBeFound("revisorSignature.doesNotContain=" + UPDATED_REVISOR_SIGNATURE);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisorNameIsEqualToSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisorName equals to DEFAULT_REVISOR_NAME
        defaultAnnualInventoryDeclarationShouldBeFound("revisorName.equals=" + DEFAULT_REVISOR_NAME);

        // Get all the annualInventoryDeclarationList where revisorName equals to UPDATED_REVISOR_NAME
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisorName.equals=" + UPDATED_REVISOR_NAME);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisorNameIsInShouldWork() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisorName in DEFAULT_REVISOR_NAME or UPDATED_REVISOR_NAME
        defaultAnnualInventoryDeclarationShouldBeFound("revisorName.in=" + DEFAULT_REVISOR_NAME + "," + UPDATED_REVISOR_NAME);

        // Get all the annualInventoryDeclarationList where revisorName equals to UPDATED_REVISOR_NAME
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisorName.in=" + UPDATED_REVISOR_NAME);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisorNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisorName is not null
        defaultAnnualInventoryDeclarationShouldBeFound("revisorName.specified=true");

        // Get all the annualInventoryDeclarationList where revisorName is null
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisorName.specified=false");
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisorNameContainsSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisorName contains DEFAULT_REVISOR_NAME
        defaultAnnualInventoryDeclarationShouldBeFound("revisorName.contains=" + DEFAULT_REVISOR_NAME);

        // Get all the annualInventoryDeclarationList where revisorName contains UPDATED_REVISOR_NAME
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisorName.contains=" + UPDATED_REVISOR_NAME);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByRevisorNameNotContainsSomething() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        // Get all the annualInventoryDeclarationList where revisorName does not contain DEFAULT_REVISOR_NAME
        defaultAnnualInventoryDeclarationShouldNotBeFound("revisorName.doesNotContain=" + DEFAULT_REVISOR_NAME);

        // Get all the annualInventoryDeclarationList where revisorName does not contain UPDATED_REVISOR_NAME
        defaultAnnualInventoryDeclarationShouldBeFound("revisorName.doesNotContain=" + UPDATED_REVISOR_NAME);
    }

    @Test
    @Transactional
    void getAllAnnualInventoryDeclarationsByApiaryInformationIsEqualToSomething() throws Exception {
        ApiaryInformation apiaryInformation;
        if (TestUtil.findAll(em, ApiaryInformation.class).isEmpty()) {
            annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);
            apiaryInformation = ApiaryInformationResourceIT.createEntity(em);
        } else {
            apiaryInformation = TestUtil.findAll(em, ApiaryInformation.class).get(0);
        }
        em.persist(apiaryInformation);
        em.flush();
        annualInventoryDeclaration.addApiaryInformation(apiaryInformation);
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);
        Long apiaryInformationId = apiaryInformation.getId();

        // Get all the annualInventoryDeclarationList where apiaryInformation equals to apiaryInformationId
        defaultAnnualInventoryDeclarationShouldBeFound("apiaryInformationId.equals=" + apiaryInformationId);

        // Get all the annualInventoryDeclarationList where apiaryInformation equals to (apiaryInformationId + 1)
        defaultAnnualInventoryDeclarationShouldNotBeFound("apiaryInformationId.equals=" + (apiaryInformationId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAnnualInventoryDeclarationShouldBeFound(String filter) throws Exception {
        restAnnualInventoryDeclarationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(annualInventoryDeclaration.getId().intValue())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL)))
            .andExpect(jsonPath("$.[*].beekeeperFaxNumber").value(hasItem(DEFAULT_BEEKEEPER_FAX_NUMBER)))
            .andExpect(jsonPath("$.[*].beekeeperCompleteName").value(hasItem(DEFAULT_BEEKEEPER_COMPLETE_NAME)))
            .andExpect(jsonPath("$.[*].beekeeperNif").value(hasItem(DEFAULT_BEEKEEPER_NIF)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].beekeeperAddress").value(hasItem(DEFAULT_BEEKEEPER_ADDRESS)))
            .andExpect(jsonPath("$.[*].beekeeperPostalCode").value(hasItem(DEFAULT_BEEKEEPER_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].beekeeperPhoneNumber").value(hasItem(DEFAULT_BEEKEEPER_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].beekeeperEntityZoneResidence").value(hasItem(DEFAULT_BEEKEEPER_ENTITY_ZONE_RESIDENCE)))
            .andExpect(jsonPath("$.[*].beekeeperNumber").value(hasItem(DEFAULT_BEEKEEPER_NUMBER)))
            .andExpect(
                jsonPath("$.[*].annualInventoryDeclarationState").value(hasItem(DEFAULT_ANNUAL_INVENTORY_DECLARATION_STATE.toString()))
            )
            .andExpect(jsonPath("$.[*].revisionDate").value(hasItem(DEFAULT_REVISION_DATE.toString())))
            .andExpect(jsonPath("$.[*].revisionLocation").value(hasItem(DEFAULT_REVISION_LOCATION)))
            .andExpect(jsonPath("$.[*].revisorSignature").value(hasItem(DEFAULT_REVISOR_SIGNATURE)))
            .andExpect(jsonPath("$.[*].revisorName").value(hasItem(DEFAULT_REVISOR_NAME)));

        // Check, that the count call also returns 1
        restAnnualInventoryDeclarationMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAnnualInventoryDeclarationShouldNotBeFound(String filter) throws Exception {
        restAnnualInventoryDeclarationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAnnualInventoryDeclarationMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingAnnualInventoryDeclaration() throws Exception {
        // Get the annualInventoryDeclaration
        restAnnualInventoryDeclarationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAnnualInventoryDeclaration() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        int databaseSizeBeforeUpdate = annualInventoryDeclarationRepository.findAll().size();

        // Update the annualInventoryDeclaration
        AnnualInventoryDeclaration updatedAnnualInventoryDeclaration = annualInventoryDeclarationRepository
            .findById(annualInventoryDeclaration.getId())
            .get();
        // Disconnect from session so that the updates on updatedAnnualInventoryDeclaration are not directly saved in db
        em.detach(updatedAnnualInventoryDeclaration);
        updatedAnnualInventoryDeclaration
            .total(UPDATED_TOTAL)
            .beekeeperFaxNumber(UPDATED_BEEKEEPER_FAX_NUMBER)
            .beekeeperCompleteName(UPDATED_BEEKEEPER_COMPLETE_NAME)
            .beekeeperNif(UPDATED_BEEKEEPER_NIF)
            .date(UPDATED_DATE)
            .beekeeperAddress(UPDATED_BEEKEEPER_ADDRESS)
            .beekeeperPostalCode(UPDATED_BEEKEEPER_POSTAL_CODE)
            .beekeeperPhoneNumber(UPDATED_BEEKEEPER_PHONE_NUMBER)
            .beekeeperEntityZoneResidence(UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE)
            .beekeeperNumber(UPDATED_BEEKEEPER_NUMBER)
            .annualInventoryDeclarationState(UPDATED_ANNUAL_INVENTORY_DECLARATION_STATE)
            .revisionDate(UPDATED_REVISION_DATE)
            .revisionLocation(UPDATED_REVISION_LOCATION)
            .revisorSignature(UPDATED_REVISOR_SIGNATURE)
            .revisorName(UPDATED_REVISOR_NAME);
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(
            updatedAnnualInventoryDeclaration
        );

        restAnnualInventoryDeclarationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, annualInventoryDeclarationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isOk());

        // Validate the AnnualInventoryDeclaration in the database
        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeUpdate);
        AnnualInventoryDeclaration testAnnualInventoryDeclaration = annualInventoryDeclarationList.get(
            annualInventoryDeclarationList.size() - 1
        );
        assertThat(testAnnualInventoryDeclaration.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperFaxNumber()).isEqualTo(UPDATED_BEEKEEPER_FAX_NUMBER);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperCompleteName()).isEqualTo(UPDATED_BEEKEEPER_COMPLETE_NAME);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperNif()).isEqualTo(UPDATED_BEEKEEPER_NIF);
        assertThat(testAnnualInventoryDeclaration.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperAddress()).isEqualTo(UPDATED_BEEKEEPER_ADDRESS);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperPostalCode()).isEqualTo(UPDATED_BEEKEEPER_POSTAL_CODE);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperPhoneNumber()).isEqualTo(UPDATED_BEEKEEPER_PHONE_NUMBER);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperEntityZoneResidence()).isEqualTo(UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperNumber()).isEqualTo(UPDATED_BEEKEEPER_NUMBER);
        assertThat(testAnnualInventoryDeclaration.getAnnualInventoryDeclarationState())
            .isEqualTo(UPDATED_ANNUAL_INVENTORY_DECLARATION_STATE);
        assertThat(testAnnualInventoryDeclaration.getRevisionDate()).isEqualTo(UPDATED_REVISION_DATE);
        assertThat(testAnnualInventoryDeclaration.getRevisionLocation()).isEqualTo(UPDATED_REVISION_LOCATION);
        assertThat(testAnnualInventoryDeclaration.getRevisorSignature()).isEqualTo(UPDATED_REVISOR_SIGNATURE);
        assertThat(testAnnualInventoryDeclaration.getRevisorName()).isEqualTo(UPDATED_REVISOR_NAME);
    }

    @Test
    @Transactional
    void putNonExistingAnnualInventoryDeclaration() throws Exception {
        int databaseSizeBeforeUpdate = annualInventoryDeclarationRepository.findAll().size();
        annualInventoryDeclaration.setId(count.incrementAndGet());

        // Create the AnnualInventoryDeclaration
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnnualInventoryDeclarationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, annualInventoryDeclarationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnnualInventoryDeclaration in the database
        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAnnualInventoryDeclaration() throws Exception {
        int databaseSizeBeforeUpdate = annualInventoryDeclarationRepository.findAll().size();
        annualInventoryDeclaration.setId(count.incrementAndGet());

        // Create the AnnualInventoryDeclaration
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnnualInventoryDeclarationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnnualInventoryDeclaration in the database
        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAnnualInventoryDeclaration() throws Exception {
        int databaseSizeBeforeUpdate = annualInventoryDeclarationRepository.findAll().size();
        annualInventoryDeclaration.setId(count.incrementAndGet());

        // Create the AnnualInventoryDeclaration
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnnualInventoryDeclarationMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnnualInventoryDeclaration in the database
        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAnnualInventoryDeclarationWithPatch() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        int databaseSizeBeforeUpdate = annualInventoryDeclarationRepository.findAll().size();

        // Update the annualInventoryDeclaration using partial update
        AnnualInventoryDeclaration partialUpdatedAnnualInventoryDeclaration = new AnnualInventoryDeclaration();
        partialUpdatedAnnualInventoryDeclaration.setId(annualInventoryDeclaration.getId());

        partialUpdatedAnnualInventoryDeclaration
            .total(UPDATED_TOTAL)
            .beekeeperPostalCode(UPDATED_BEEKEEPER_POSTAL_CODE)
            .beekeeperNumber(UPDATED_BEEKEEPER_NUMBER)
            .revisionLocation(UPDATED_REVISION_LOCATION)
            .revisorName(UPDATED_REVISOR_NAME);

        restAnnualInventoryDeclarationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnnualInventoryDeclaration.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnnualInventoryDeclaration))
            )
            .andExpect(status().isOk());

        // Validate the AnnualInventoryDeclaration in the database
        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeUpdate);
        AnnualInventoryDeclaration testAnnualInventoryDeclaration = annualInventoryDeclarationList.get(
            annualInventoryDeclarationList.size() - 1
        );
        assertThat(testAnnualInventoryDeclaration.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperFaxNumber()).isEqualTo(DEFAULT_BEEKEEPER_FAX_NUMBER);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperCompleteName()).isEqualTo(DEFAULT_BEEKEEPER_COMPLETE_NAME);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperNif()).isEqualTo(DEFAULT_BEEKEEPER_NIF);
        assertThat(testAnnualInventoryDeclaration.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperAddress()).isEqualTo(DEFAULT_BEEKEEPER_ADDRESS);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperPostalCode()).isEqualTo(UPDATED_BEEKEEPER_POSTAL_CODE);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperPhoneNumber()).isEqualTo(DEFAULT_BEEKEEPER_PHONE_NUMBER);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperEntityZoneResidence()).isEqualTo(DEFAULT_BEEKEEPER_ENTITY_ZONE_RESIDENCE);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperNumber()).isEqualTo(UPDATED_BEEKEEPER_NUMBER);
        assertThat(testAnnualInventoryDeclaration.getAnnualInventoryDeclarationState())
            .isEqualTo(DEFAULT_ANNUAL_INVENTORY_DECLARATION_STATE);
        assertThat(testAnnualInventoryDeclaration.getRevisionDate()).isEqualTo(DEFAULT_REVISION_DATE);
        assertThat(testAnnualInventoryDeclaration.getRevisionLocation()).isEqualTo(UPDATED_REVISION_LOCATION);
        assertThat(testAnnualInventoryDeclaration.getRevisorSignature()).isEqualTo(DEFAULT_REVISOR_SIGNATURE);
        assertThat(testAnnualInventoryDeclaration.getRevisorName()).isEqualTo(UPDATED_REVISOR_NAME);
    }

    @Test
    @Transactional
    void fullUpdateAnnualInventoryDeclarationWithPatch() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        int databaseSizeBeforeUpdate = annualInventoryDeclarationRepository.findAll().size();

        // Update the annualInventoryDeclaration using partial update
        AnnualInventoryDeclaration partialUpdatedAnnualInventoryDeclaration = new AnnualInventoryDeclaration();
        partialUpdatedAnnualInventoryDeclaration.setId(annualInventoryDeclaration.getId());

        partialUpdatedAnnualInventoryDeclaration
            .total(UPDATED_TOTAL)
            .beekeeperFaxNumber(UPDATED_BEEKEEPER_FAX_NUMBER)
            .beekeeperCompleteName(UPDATED_BEEKEEPER_COMPLETE_NAME)
            .beekeeperNif(UPDATED_BEEKEEPER_NIF)
            .date(UPDATED_DATE)
            .beekeeperAddress(UPDATED_BEEKEEPER_ADDRESS)
            .beekeeperPostalCode(UPDATED_BEEKEEPER_POSTAL_CODE)
            .beekeeperPhoneNumber(UPDATED_BEEKEEPER_PHONE_NUMBER)
            .beekeeperEntityZoneResidence(UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE)
            .beekeeperNumber(UPDATED_BEEKEEPER_NUMBER)
            .annualInventoryDeclarationState(UPDATED_ANNUAL_INVENTORY_DECLARATION_STATE)
            .revisionDate(UPDATED_REVISION_DATE)
            .revisionLocation(UPDATED_REVISION_LOCATION)
            .revisorSignature(UPDATED_REVISOR_SIGNATURE)
            .revisorName(UPDATED_REVISOR_NAME);

        restAnnualInventoryDeclarationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnnualInventoryDeclaration.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnnualInventoryDeclaration))
            )
            .andExpect(status().isOk());

        // Validate the AnnualInventoryDeclaration in the database
        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeUpdate);
        AnnualInventoryDeclaration testAnnualInventoryDeclaration = annualInventoryDeclarationList.get(
            annualInventoryDeclarationList.size() - 1
        );
        assertThat(testAnnualInventoryDeclaration.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperFaxNumber()).isEqualTo(UPDATED_BEEKEEPER_FAX_NUMBER);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperCompleteName()).isEqualTo(UPDATED_BEEKEEPER_COMPLETE_NAME);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperNif()).isEqualTo(UPDATED_BEEKEEPER_NIF);
        assertThat(testAnnualInventoryDeclaration.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperAddress()).isEqualTo(UPDATED_BEEKEEPER_ADDRESS);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperPostalCode()).isEqualTo(UPDATED_BEEKEEPER_POSTAL_CODE);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperPhoneNumber()).isEqualTo(UPDATED_BEEKEEPER_PHONE_NUMBER);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperEntityZoneResidence()).isEqualTo(UPDATED_BEEKEEPER_ENTITY_ZONE_RESIDENCE);
        assertThat(testAnnualInventoryDeclaration.getBeekeeperNumber()).isEqualTo(UPDATED_BEEKEEPER_NUMBER);
        assertThat(testAnnualInventoryDeclaration.getAnnualInventoryDeclarationState())
            .isEqualTo(UPDATED_ANNUAL_INVENTORY_DECLARATION_STATE);
        assertThat(testAnnualInventoryDeclaration.getRevisionDate()).isEqualTo(UPDATED_REVISION_DATE);
        assertThat(testAnnualInventoryDeclaration.getRevisionLocation()).isEqualTo(UPDATED_REVISION_LOCATION);
        assertThat(testAnnualInventoryDeclaration.getRevisorSignature()).isEqualTo(UPDATED_REVISOR_SIGNATURE);
        assertThat(testAnnualInventoryDeclaration.getRevisorName()).isEqualTo(UPDATED_REVISOR_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingAnnualInventoryDeclaration() throws Exception {
        int databaseSizeBeforeUpdate = annualInventoryDeclarationRepository.findAll().size();
        annualInventoryDeclaration.setId(count.incrementAndGet());

        // Create the AnnualInventoryDeclaration
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnnualInventoryDeclarationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, annualInventoryDeclarationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnnualInventoryDeclaration in the database
        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAnnualInventoryDeclaration() throws Exception {
        int databaseSizeBeforeUpdate = annualInventoryDeclarationRepository.findAll().size();
        annualInventoryDeclaration.setId(count.incrementAndGet());

        // Create the AnnualInventoryDeclaration
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnnualInventoryDeclarationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnnualInventoryDeclaration in the database
        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAnnualInventoryDeclaration() throws Exception {
        int databaseSizeBeforeUpdate = annualInventoryDeclarationRepository.findAll().size();
        annualInventoryDeclaration.setId(count.incrementAndGet());

        // Create the AnnualInventoryDeclaration
        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnnualInventoryDeclarationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(annualInventoryDeclarationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnnualInventoryDeclaration in the database
        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAnnualInventoryDeclaration() throws Exception {
        // Initialize the database
        annualInventoryDeclarationRepository.saveAndFlush(annualInventoryDeclaration);

        int databaseSizeBeforeDelete = annualInventoryDeclarationRepository.findAll().size();

        // Delete the annualInventoryDeclaration
        restAnnualInventoryDeclarationMockMvc
            .perform(delete(ENTITY_API_URL_ID, annualInventoryDeclaration.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AnnualInventoryDeclaration> annualInventoryDeclarationList = annualInventoryDeclarationRepository.findAll();
        assertThat(annualInventoryDeclarationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
