package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Apiary;
import com.mycompany.myapp.domain.Beekeeper;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.BeekeeperRepository;
import com.mycompany.myapp.service.criteria.BeekeeperCriteria;
import com.mycompany.myapp.service.dto.BeekeeperDTO;
import com.mycompany.myapp.service.mapper.BeekeeperMapper;
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
 * Integration tests for the {@link BeekeeperResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BeekeeperResourceIT {

    private static final String DEFAULT_BEEKEEPER_COMPLETE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BEEKEEPER_COMPLETE_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_BEEKEEPER_NUMBER = 1;
    private static final Integer UPDATED_BEEKEEPER_NUMBER = 2;
    private static final Integer SMALLER_BEEKEEPER_NUMBER = 1 - 1;

    private static final Integer DEFAULT_ENTITY_ZONE_RESIDENCE = 1;
    private static final Integer UPDATED_ENTITY_ZONE_RESIDENCE = 2;
    private static final Integer SMALLER_ENTITY_ZONE_RESIDENCE = 1 - 1;

    private static final Integer DEFAULT_NIF = 1;
    private static final Integer UPDATED_NIF = 2;
    private static final Integer SMALLER_NIF = 1 - 1;

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PHONE_NUMBER = 1;
    private static final Integer UPDATED_PHONE_NUMBER = 2;
    private static final Integer SMALLER_PHONE_NUMBER = 1 - 1;

    private static final Integer DEFAULT_FAX_NUMBER = 1;
    private static final Integer UPDATED_FAX_NUMBER = 2;
    private static final Integer SMALLER_FAX_NUMBER = 1 - 1;

    private static final String ENTITY_API_URL = "/api/beekeepers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BeekeeperRepository beekeeperRepository;

    @Autowired
    private BeekeeperMapper beekeeperMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBeekeeperMockMvc;

    private Beekeeper beekeeper;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Beekeeper createEntity(EntityManager em) {
        Beekeeper beekeeper = new Beekeeper()
            .beekeeperCompleteName(DEFAULT_BEEKEEPER_COMPLETE_NAME)
            .beekeeperNumber(DEFAULT_BEEKEEPER_NUMBER)
            .entityZoneResidence(DEFAULT_ENTITY_ZONE_RESIDENCE)
            .nif(DEFAULT_NIF)
            .address(DEFAULT_ADDRESS)
            .postalCode(DEFAULT_POSTAL_CODE)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .faxNumber(DEFAULT_FAX_NUMBER);
        return beekeeper;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Beekeeper createUpdatedEntity(EntityManager em) {
        Beekeeper beekeeper = new Beekeeper()
            .beekeeperCompleteName(UPDATED_BEEKEEPER_COMPLETE_NAME)
            .beekeeperNumber(UPDATED_BEEKEEPER_NUMBER)
            .entityZoneResidence(UPDATED_ENTITY_ZONE_RESIDENCE)
            .nif(UPDATED_NIF)
            .address(UPDATED_ADDRESS)
            .postalCode(UPDATED_POSTAL_CODE)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .faxNumber(UPDATED_FAX_NUMBER);
        return beekeeper;
    }

    @BeforeEach
    public void initTest() {
        beekeeper = createEntity(em);
    }

    @Test
    @Transactional
    void createBeekeeper() throws Exception {
        int databaseSizeBeforeCreate = beekeeperRepository.findAll().size();
        // Create the Beekeeper
        BeekeeperDTO beekeeperDTO = beekeeperMapper.toDto(beekeeper);
        restBeekeeperMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(beekeeperDTO)))
            .andExpect(status().isCreated());

        // Validate the Beekeeper in the database
        List<Beekeeper> beekeeperList = beekeeperRepository.findAll();
        assertThat(beekeeperList).hasSize(databaseSizeBeforeCreate + 1);
        Beekeeper testBeekeeper = beekeeperList.get(beekeeperList.size() - 1);
        assertThat(testBeekeeper.getBeekeeperCompleteName()).isEqualTo(DEFAULT_BEEKEEPER_COMPLETE_NAME);
        assertThat(testBeekeeper.getBeekeeperNumber()).isEqualTo(DEFAULT_BEEKEEPER_NUMBER);
        assertThat(testBeekeeper.getEntityZoneResidence()).isEqualTo(DEFAULT_ENTITY_ZONE_RESIDENCE);
        assertThat(testBeekeeper.getNif()).isEqualTo(DEFAULT_NIF);
        assertThat(testBeekeeper.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testBeekeeper.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testBeekeeper.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testBeekeeper.getFaxNumber()).isEqualTo(DEFAULT_FAX_NUMBER);
    }

    @Test
    @Transactional
    void createBeekeeperWithExistingId() throws Exception {
        // Create the Beekeeper with an existing ID
        beekeeper.setId(1L);
        BeekeeperDTO beekeeperDTO = beekeeperMapper.toDto(beekeeper);

        int databaseSizeBeforeCreate = beekeeperRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBeekeeperMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(beekeeperDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Beekeeper in the database
        List<Beekeeper> beekeeperList = beekeeperRepository.findAll();
        assertThat(beekeeperList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBeekeepers() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList
        restBeekeeperMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(beekeeper.getId().intValue())))
            .andExpect(jsonPath("$.[*].beekeeperCompleteName").value(hasItem(DEFAULT_BEEKEEPER_COMPLETE_NAME)))
            .andExpect(jsonPath("$.[*].beekeeperNumber").value(hasItem(DEFAULT_BEEKEEPER_NUMBER)))
            .andExpect(jsonPath("$.[*].entityZoneResidence").value(hasItem(DEFAULT_ENTITY_ZONE_RESIDENCE)))
            .andExpect(jsonPath("$.[*].nif").value(hasItem(DEFAULT_NIF)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].faxNumber").value(hasItem(DEFAULT_FAX_NUMBER)));
    }

    @Test
    @Transactional
    void getBeekeeper() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get the beekeeper
        restBeekeeperMockMvc
            .perform(get(ENTITY_API_URL_ID, beekeeper.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(beekeeper.getId().intValue()))
            .andExpect(jsonPath("$.beekeeperCompleteName").value(DEFAULT_BEEKEEPER_COMPLETE_NAME))
            .andExpect(jsonPath("$.beekeeperNumber").value(DEFAULT_BEEKEEPER_NUMBER))
            .andExpect(jsonPath("$.entityZoneResidence").value(DEFAULT_ENTITY_ZONE_RESIDENCE))
            .andExpect(jsonPath("$.nif").value(DEFAULT_NIF))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.faxNumber").value(DEFAULT_FAX_NUMBER));
    }

    @Test
    @Transactional
    void getBeekeepersByIdFiltering() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        Long id = beekeeper.getId();

        defaultBeekeeperShouldBeFound("id.equals=" + id);
        defaultBeekeeperShouldNotBeFound("id.notEquals=" + id);

        defaultBeekeeperShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultBeekeeperShouldNotBeFound("id.greaterThan=" + id);

        defaultBeekeeperShouldBeFound("id.lessThanOrEqual=" + id);
        defaultBeekeeperShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllBeekeepersByBeekeeperCompleteNameIsEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where beekeeperCompleteName equals to DEFAULT_BEEKEEPER_COMPLETE_NAME
        defaultBeekeeperShouldBeFound("beekeeperCompleteName.equals=" + DEFAULT_BEEKEEPER_COMPLETE_NAME);

        // Get all the beekeeperList where beekeeperCompleteName equals to UPDATED_BEEKEEPER_COMPLETE_NAME
        defaultBeekeeperShouldNotBeFound("beekeeperCompleteName.equals=" + UPDATED_BEEKEEPER_COMPLETE_NAME);
    }

    @Test
    @Transactional
    void getAllBeekeepersByBeekeeperCompleteNameIsInShouldWork() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where beekeeperCompleteName in DEFAULT_BEEKEEPER_COMPLETE_NAME or UPDATED_BEEKEEPER_COMPLETE_NAME
        defaultBeekeeperShouldBeFound(
            "beekeeperCompleteName.in=" + DEFAULT_BEEKEEPER_COMPLETE_NAME + "," + UPDATED_BEEKEEPER_COMPLETE_NAME
        );

        // Get all the beekeeperList where beekeeperCompleteName equals to UPDATED_BEEKEEPER_COMPLETE_NAME
        defaultBeekeeperShouldNotBeFound("beekeeperCompleteName.in=" + UPDATED_BEEKEEPER_COMPLETE_NAME);
    }

    @Test
    @Transactional
    void getAllBeekeepersByBeekeeperCompleteNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where beekeeperCompleteName is not null
        defaultBeekeeperShouldBeFound("beekeeperCompleteName.specified=true");

        // Get all the beekeeperList where beekeeperCompleteName is null
        defaultBeekeeperShouldNotBeFound("beekeeperCompleteName.specified=false");
    }

    @Test
    @Transactional
    void getAllBeekeepersByBeekeeperCompleteNameContainsSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where beekeeperCompleteName contains DEFAULT_BEEKEEPER_COMPLETE_NAME
        defaultBeekeeperShouldBeFound("beekeeperCompleteName.contains=" + DEFAULT_BEEKEEPER_COMPLETE_NAME);

        // Get all the beekeeperList where beekeeperCompleteName contains UPDATED_BEEKEEPER_COMPLETE_NAME
        defaultBeekeeperShouldNotBeFound("beekeeperCompleteName.contains=" + UPDATED_BEEKEEPER_COMPLETE_NAME);
    }

    @Test
    @Transactional
    void getAllBeekeepersByBeekeeperCompleteNameNotContainsSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where beekeeperCompleteName does not contain DEFAULT_BEEKEEPER_COMPLETE_NAME
        defaultBeekeeperShouldNotBeFound("beekeeperCompleteName.doesNotContain=" + DEFAULT_BEEKEEPER_COMPLETE_NAME);

        // Get all the beekeeperList where beekeeperCompleteName does not contain UPDATED_BEEKEEPER_COMPLETE_NAME
        defaultBeekeeperShouldBeFound("beekeeperCompleteName.doesNotContain=" + UPDATED_BEEKEEPER_COMPLETE_NAME);
    }

    @Test
    @Transactional
    void getAllBeekeepersByBeekeeperNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where beekeeperNumber equals to DEFAULT_BEEKEEPER_NUMBER
        defaultBeekeeperShouldBeFound("beekeeperNumber.equals=" + DEFAULT_BEEKEEPER_NUMBER);

        // Get all the beekeeperList where beekeeperNumber equals to UPDATED_BEEKEEPER_NUMBER
        defaultBeekeeperShouldNotBeFound("beekeeperNumber.equals=" + UPDATED_BEEKEEPER_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByBeekeeperNumberIsInShouldWork() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where beekeeperNumber in DEFAULT_BEEKEEPER_NUMBER or UPDATED_BEEKEEPER_NUMBER
        defaultBeekeeperShouldBeFound("beekeeperNumber.in=" + DEFAULT_BEEKEEPER_NUMBER + "," + UPDATED_BEEKEEPER_NUMBER);

        // Get all the beekeeperList where beekeeperNumber equals to UPDATED_BEEKEEPER_NUMBER
        defaultBeekeeperShouldNotBeFound("beekeeperNumber.in=" + UPDATED_BEEKEEPER_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByBeekeeperNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where beekeeperNumber is not null
        defaultBeekeeperShouldBeFound("beekeeperNumber.specified=true");

        // Get all the beekeeperList where beekeeperNumber is null
        defaultBeekeeperShouldNotBeFound("beekeeperNumber.specified=false");
    }

    @Test
    @Transactional
    void getAllBeekeepersByBeekeeperNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where beekeeperNumber is greater than or equal to DEFAULT_BEEKEEPER_NUMBER
        defaultBeekeeperShouldBeFound("beekeeperNumber.greaterThanOrEqual=" + DEFAULT_BEEKEEPER_NUMBER);

        // Get all the beekeeperList where beekeeperNumber is greater than or equal to UPDATED_BEEKEEPER_NUMBER
        defaultBeekeeperShouldNotBeFound("beekeeperNumber.greaterThanOrEqual=" + UPDATED_BEEKEEPER_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByBeekeeperNumberIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where beekeeperNumber is less than or equal to DEFAULT_BEEKEEPER_NUMBER
        defaultBeekeeperShouldBeFound("beekeeperNumber.lessThanOrEqual=" + DEFAULT_BEEKEEPER_NUMBER);

        // Get all the beekeeperList where beekeeperNumber is less than or equal to SMALLER_BEEKEEPER_NUMBER
        defaultBeekeeperShouldNotBeFound("beekeeperNumber.lessThanOrEqual=" + SMALLER_BEEKEEPER_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByBeekeeperNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where beekeeperNumber is less than DEFAULT_BEEKEEPER_NUMBER
        defaultBeekeeperShouldNotBeFound("beekeeperNumber.lessThan=" + DEFAULT_BEEKEEPER_NUMBER);

        // Get all the beekeeperList where beekeeperNumber is less than UPDATED_BEEKEEPER_NUMBER
        defaultBeekeeperShouldBeFound("beekeeperNumber.lessThan=" + UPDATED_BEEKEEPER_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByBeekeeperNumberIsGreaterThanSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where beekeeperNumber is greater than DEFAULT_BEEKEEPER_NUMBER
        defaultBeekeeperShouldNotBeFound("beekeeperNumber.greaterThan=" + DEFAULT_BEEKEEPER_NUMBER);

        // Get all the beekeeperList where beekeeperNumber is greater than SMALLER_BEEKEEPER_NUMBER
        defaultBeekeeperShouldBeFound("beekeeperNumber.greaterThan=" + SMALLER_BEEKEEPER_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByEntityZoneResidenceIsEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where entityZoneResidence equals to DEFAULT_ENTITY_ZONE_RESIDENCE
        defaultBeekeeperShouldBeFound("entityZoneResidence.equals=" + DEFAULT_ENTITY_ZONE_RESIDENCE);

        // Get all the beekeeperList where entityZoneResidence equals to UPDATED_ENTITY_ZONE_RESIDENCE
        defaultBeekeeperShouldNotBeFound("entityZoneResidence.equals=" + UPDATED_ENTITY_ZONE_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllBeekeepersByEntityZoneResidenceIsInShouldWork() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where entityZoneResidence in DEFAULT_ENTITY_ZONE_RESIDENCE or UPDATED_ENTITY_ZONE_RESIDENCE
        defaultBeekeeperShouldBeFound("entityZoneResidence.in=" + DEFAULT_ENTITY_ZONE_RESIDENCE + "," + UPDATED_ENTITY_ZONE_RESIDENCE);

        // Get all the beekeeperList where entityZoneResidence equals to UPDATED_ENTITY_ZONE_RESIDENCE
        defaultBeekeeperShouldNotBeFound("entityZoneResidence.in=" + UPDATED_ENTITY_ZONE_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllBeekeepersByEntityZoneResidenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where entityZoneResidence is not null
        defaultBeekeeperShouldBeFound("entityZoneResidence.specified=true");

        // Get all the beekeeperList where entityZoneResidence is null
        defaultBeekeeperShouldNotBeFound("entityZoneResidence.specified=false");
    }

    @Test
    @Transactional
    void getAllBeekeepersByEntityZoneResidenceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where entityZoneResidence is greater than or equal to DEFAULT_ENTITY_ZONE_RESIDENCE
        defaultBeekeeperShouldBeFound("entityZoneResidence.greaterThanOrEqual=" + DEFAULT_ENTITY_ZONE_RESIDENCE);

        // Get all the beekeeperList where entityZoneResidence is greater than or equal to UPDATED_ENTITY_ZONE_RESIDENCE
        defaultBeekeeperShouldNotBeFound("entityZoneResidence.greaterThanOrEqual=" + UPDATED_ENTITY_ZONE_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllBeekeepersByEntityZoneResidenceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where entityZoneResidence is less than or equal to DEFAULT_ENTITY_ZONE_RESIDENCE
        defaultBeekeeperShouldBeFound("entityZoneResidence.lessThanOrEqual=" + DEFAULT_ENTITY_ZONE_RESIDENCE);

        // Get all the beekeeperList where entityZoneResidence is less than or equal to SMALLER_ENTITY_ZONE_RESIDENCE
        defaultBeekeeperShouldNotBeFound("entityZoneResidence.lessThanOrEqual=" + SMALLER_ENTITY_ZONE_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllBeekeepersByEntityZoneResidenceIsLessThanSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where entityZoneResidence is less than DEFAULT_ENTITY_ZONE_RESIDENCE
        defaultBeekeeperShouldNotBeFound("entityZoneResidence.lessThan=" + DEFAULT_ENTITY_ZONE_RESIDENCE);

        // Get all the beekeeperList where entityZoneResidence is less than UPDATED_ENTITY_ZONE_RESIDENCE
        defaultBeekeeperShouldBeFound("entityZoneResidence.lessThan=" + UPDATED_ENTITY_ZONE_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllBeekeepersByEntityZoneResidenceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where entityZoneResidence is greater than DEFAULT_ENTITY_ZONE_RESIDENCE
        defaultBeekeeperShouldNotBeFound("entityZoneResidence.greaterThan=" + DEFAULT_ENTITY_ZONE_RESIDENCE);

        // Get all the beekeeperList where entityZoneResidence is greater than SMALLER_ENTITY_ZONE_RESIDENCE
        defaultBeekeeperShouldBeFound("entityZoneResidence.greaterThan=" + SMALLER_ENTITY_ZONE_RESIDENCE);
    }

    @Test
    @Transactional
    void getAllBeekeepersByNifIsEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where nif equals to DEFAULT_NIF
        defaultBeekeeperShouldBeFound("nif.equals=" + DEFAULT_NIF);

        // Get all the beekeeperList where nif equals to UPDATED_NIF
        defaultBeekeeperShouldNotBeFound("nif.equals=" + UPDATED_NIF);
    }

    @Test
    @Transactional
    void getAllBeekeepersByNifIsInShouldWork() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where nif in DEFAULT_NIF or UPDATED_NIF
        defaultBeekeeperShouldBeFound("nif.in=" + DEFAULT_NIF + "," + UPDATED_NIF);

        // Get all the beekeeperList where nif equals to UPDATED_NIF
        defaultBeekeeperShouldNotBeFound("nif.in=" + UPDATED_NIF);
    }

    @Test
    @Transactional
    void getAllBeekeepersByNifIsNullOrNotNull() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where nif is not null
        defaultBeekeeperShouldBeFound("nif.specified=true");

        // Get all the beekeeperList where nif is null
        defaultBeekeeperShouldNotBeFound("nif.specified=false");
    }

    @Test
    @Transactional
    void getAllBeekeepersByNifIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where nif is greater than or equal to DEFAULT_NIF
        defaultBeekeeperShouldBeFound("nif.greaterThanOrEqual=" + DEFAULT_NIF);

        // Get all the beekeeperList where nif is greater than or equal to UPDATED_NIF
        defaultBeekeeperShouldNotBeFound("nif.greaterThanOrEqual=" + UPDATED_NIF);
    }

    @Test
    @Transactional
    void getAllBeekeepersByNifIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where nif is less than or equal to DEFAULT_NIF
        defaultBeekeeperShouldBeFound("nif.lessThanOrEqual=" + DEFAULT_NIF);

        // Get all the beekeeperList where nif is less than or equal to SMALLER_NIF
        defaultBeekeeperShouldNotBeFound("nif.lessThanOrEqual=" + SMALLER_NIF);
    }

    @Test
    @Transactional
    void getAllBeekeepersByNifIsLessThanSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where nif is less than DEFAULT_NIF
        defaultBeekeeperShouldNotBeFound("nif.lessThan=" + DEFAULT_NIF);

        // Get all the beekeeperList where nif is less than UPDATED_NIF
        defaultBeekeeperShouldBeFound("nif.lessThan=" + UPDATED_NIF);
    }

    @Test
    @Transactional
    void getAllBeekeepersByNifIsGreaterThanSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where nif is greater than DEFAULT_NIF
        defaultBeekeeperShouldNotBeFound("nif.greaterThan=" + DEFAULT_NIF);

        // Get all the beekeeperList where nif is greater than SMALLER_NIF
        defaultBeekeeperShouldBeFound("nif.greaterThan=" + SMALLER_NIF);
    }

    @Test
    @Transactional
    void getAllBeekeepersByAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where address equals to DEFAULT_ADDRESS
        defaultBeekeeperShouldBeFound("address.equals=" + DEFAULT_ADDRESS);

        // Get all the beekeeperList where address equals to UPDATED_ADDRESS
        defaultBeekeeperShouldNotBeFound("address.equals=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void getAllBeekeepersByAddressIsInShouldWork() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where address in DEFAULT_ADDRESS or UPDATED_ADDRESS
        defaultBeekeeperShouldBeFound("address.in=" + DEFAULT_ADDRESS + "," + UPDATED_ADDRESS);

        // Get all the beekeeperList where address equals to UPDATED_ADDRESS
        defaultBeekeeperShouldNotBeFound("address.in=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void getAllBeekeepersByAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where address is not null
        defaultBeekeeperShouldBeFound("address.specified=true");

        // Get all the beekeeperList where address is null
        defaultBeekeeperShouldNotBeFound("address.specified=false");
    }

    @Test
    @Transactional
    void getAllBeekeepersByAddressContainsSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where address contains DEFAULT_ADDRESS
        defaultBeekeeperShouldBeFound("address.contains=" + DEFAULT_ADDRESS);

        // Get all the beekeeperList where address contains UPDATED_ADDRESS
        defaultBeekeeperShouldNotBeFound("address.contains=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void getAllBeekeepersByAddressNotContainsSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where address does not contain DEFAULT_ADDRESS
        defaultBeekeeperShouldNotBeFound("address.doesNotContain=" + DEFAULT_ADDRESS);

        // Get all the beekeeperList where address does not contain UPDATED_ADDRESS
        defaultBeekeeperShouldBeFound("address.doesNotContain=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void getAllBeekeepersByPostalCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where postalCode equals to DEFAULT_POSTAL_CODE
        defaultBeekeeperShouldBeFound("postalCode.equals=" + DEFAULT_POSTAL_CODE);

        // Get all the beekeeperList where postalCode equals to UPDATED_POSTAL_CODE
        defaultBeekeeperShouldNotBeFound("postalCode.equals=" + UPDATED_POSTAL_CODE);
    }

    @Test
    @Transactional
    void getAllBeekeepersByPostalCodeIsInShouldWork() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where postalCode in DEFAULT_POSTAL_CODE or UPDATED_POSTAL_CODE
        defaultBeekeeperShouldBeFound("postalCode.in=" + DEFAULT_POSTAL_CODE + "," + UPDATED_POSTAL_CODE);

        // Get all the beekeeperList where postalCode equals to UPDATED_POSTAL_CODE
        defaultBeekeeperShouldNotBeFound("postalCode.in=" + UPDATED_POSTAL_CODE);
    }

    @Test
    @Transactional
    void getAllBeekeepersByPostalCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where postalCode is not null
        defaultBeekeeperShouldBeFound("postalCode.specified=true");

        // Get all the beekeeperList where postalCode is null
        defaultBeekeeperShouldNotBeFound("postalCode.specified=false");
    }

    @Test
    @Transactional
    void getAllBeekeepersByPostalCodeContainsSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where postalCode contains DEFAULT_POSTAL_CODE
        defaultBeekeeperShouldBeFound("postalCode.contains=" + DEFAULT_POSTAL_CODE);

        // Get all the beekeeperList where postalCode contains UPDATED_POSTAL_CODE
        defaultBeekeeperShouldNotBeFound("postalCode.contains=" + UPDATED_POSTAL_CODE);
    }

    @Test
    @Transactional
    void getAllBeekeepersByPostalCodeNotContainsSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where postalCode does not contain DEFAULT_POSTAL_CODE
        defaultBeekeeperShouldNotBeFound("postalCode.doesNotContain=" + DEFAULT_POSTAL_CODE);

        // Get all the beekeeperList where postalCode does not contain UPDATED_POSTAL_CODE
        defaultBeekeeperShouldBeFound("postalCode.doesNotContain=" + UPDATED_POSTAL_CODE);
    }

    @Test
    @Transactional
    void getAllBeekeepersByPhoneNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where phoneNumber equals to DEFAULT_PHONE_NUMBER
        defaultBeekeeperShouldBeFound("phoneNumber.equals=" + DEFAULT_PHONE_NUMBER);

        // Get all the beekeeperList where phoneNumber equals to UPDATED_PHONE_NUMBER
        defaultBeekeeperShouldNotBeFound("phoneNumber.equals=" + UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByPhoneNumberIsInShouldWork() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where phoneNumber in DEFAULT_PHONE_NUMBER or UPDATED_PHONE_NUMBER
        defaultBeekeeperShouldBeFound("phoneNumber.in=" + DEFAULT_PHONE_NUMBER + "," + UPDATED_PHONE_NUMBER);

        // Get all the beekeeperList where phoneNumber equals to UPDATED_PHONE_NUMBER
        defaultBeekeeperShouldNotBeFound("phoneNumber.in=" + UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByPhoneNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where phoneNumber is not null
        defaultBeekeeperShouldBeFound("phoneNumber.specified=true");

        // Get all the beekeeperList where phoneNumber is null
        defaultBeekeeperShouldNotBeFound("phoneNumber.specified=false");
    }

    @Test
    @Transactional
    void getAllBeekeepersByPhoneNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where phoneNumber is greater than or equal to DEFAULT_PHONE_NUMBER
        defaultBeekeeperShouldBeFound("phoneNumber.greaterThanOrEqual=" + DEFAULT_PHONE_NUMBER);

        // Get all the beekeeperList where phoneNumber is greater than or equal to UPDATED_PHONE_NUMBER
        defaultBeekeeperShouldNotBeFound("phoneNumber.greaterThanOrEqual=" + UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByPhoneNumberIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where phoneNumber is less than or equal to DEFAULT_PHONE_NUMBER
        defaultBeekeeperShouldBeFound("phoneNumber.lessThanOrEqual=" + DEFAULT_PHONE_NUMBER);

        // Get all the beekeeperList where phoneNumber is less than or equal to SMALLER_PHONE_NUMBER
        defaultBeekeeperShouldNotBeFound("phoneNumber.lessThanOrEqual=" + SMALLER_PHONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByPhoneNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where phoneNumber is less than DEFAULT_PHONE_NUMBER
        defaultBeekeeperShouldNotBeFound("phoneNumber.lessThan=" + DEFAULT_PHONE_NUMBER);

        // Get all the beekeeperList where phoneNumber is less than UPDATED_PHONE_NUMBER
        defaultBeekeeperShouldBeFound("phoneNumber.lessThan=" + UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByPhoneNumberIsGreaterThanSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where phoneNumber is greater than DEFAULT_PHONE_NUMBER
        defaultBeekeeperShouldNotBeFound("phoneNumber.greaterThan=" + DEFAULT_PHONE_NUMBER);

        // Get all the beekeeperList where phoneNumber is greater than SMALLER_PHONE_NUMBER
        defaultBeekeeperShouldBeFound("phoneNumber.greaterThan=" + SMALLER_PHONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByFaxNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where faxNumber equals to DEFAULT_FAX_NUMBER
        defaultBeekeeperShouldBeFound("faxNumber.equals=" + DEFAULT_FAX_NUMBER);

        // Get all the beekeeperList where faxNumber equals to UPDATED_FAX_NUMBER
        defaultBeekeeperShouldNotBeFound("faxNumber.equals=" + UPDATED_FAX_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByFaxNumberIsInShouldWork() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where faxNumber in DEFAULT_FAX_NUMBER or UPDATED_FAX_NUMBER
        defaultBeekeeperShouldBeFound("faxNumber.in=" + DEFAULT_FAX_NUMBER + "," + UPDATED_FAX_NUMBER);

        // Get all the beekeeperList where faxNumber equals to UPDATED_FAX_NUMBER
        defaultBeekeeperShouldNotBeFound("faxNumber.in=" + UPDATED_FAX_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByFaxNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where faxNumber is not null
        defaultBeekeeperShouldBeFound("faxNumber.specified=true");

        // Get all the beekeeperList where faxNumber is null
        defaultBeekeeperShouldNotBeFound("faxNumber.specified=false");
    }

    @Test
    @Transactional
    void getAllBeekeepersByFaxNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where faxNumber is greater than or equal to DEFAULT_FAX_NUMBER
        defaultBeekeeperShouldBeFound("faxNumber.greaterThanOrEqual=" + DEFAULT_FAX_NUMBER);

        // Get all the beekeeperList where faxNumber is greater than or equal to UPDATED_FAX_NUMBER
        defaultBeekeeperShouldNotBeFound("faxNumber.greaterThanOrEqual=" + UPDATED_FAX_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByFaxNumberIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where faxNumber is less than or equal to DEFAULT_FAX_NUMBER
        defaultBeekeeperShouldBeFound("faxNumber.lessThanOrEqual=" + DEFAULT_FAX_NUMBER);

        // Get all the beekeeperList where faxNumber is less than or equal to SMALLER_FAX_NUMBER
        defaultBeekeeperShouldNotBeFound("faxNumber.lessThanOrEqual=" + SMALLER_FAX_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByFaxNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where faxNumber is less than DEFAULT_FAX_NUMBER
        defaultBeekeeperShouldNotBeFound("faxNumber.lessThan=" + DEFAULT_FAX_NUMBER);

        // Get all the beekeeperList where faxNumber is less than UPDATED_FAX_NUMBER
        defaultBeekeeperShouldBeFound("faxNumber.lessThan=" + UPDATED_FAX_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByFaxNumberIsGreaterThanSomething() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        // Get all the beekeeperList where faxNumber is greater than DEFAULT_FAX_NUMBER
        defaultBeekeeperShouldNotBeFound("faxNumber.greaterThan=" + DEFAULT_FAX_NUMBER);

        // Get all the beekeeperList where faxNumber is greater than SMALLER_FAX_NUMBER
        defaultBeekeeperShouldBeFound("faxNumber.greaterThan=" + SMALLER_FAX_NUMBER);
    }

    @Test
    @Transactional
    void getAllBeekeepersByUserIsEqualToSomething() throws Exception {
        User user;
        if (TestUtil.findAll(em, User.class).isEmpty()) {
            beekeeperRepository.saveAndFlush(beekeeper);
            user = UserResourceIT.createEntity(em);
        } else {
            user = TestUtil.findAll(em, User.class).get(0);
        }
        em.persist(user);
        em.flush();
        beekeeper.setUser(user);
        beekeeperRepository.saveAndFlush(beekeeper);
        Long userId = user.getId();

        // Get all the beekeeperList where user equals to userId
        defaultBeekeeperShouldBeFound("userId.equals=" + userId);

        // Get all the beekeeperList where user equals to (userId + 1)
        defaultBeekeeperShouldNotBeFound("userId.equals=" + (userId + 1));
    }

    @Test
    @Transactional
    void getAllBeekeepersByApiaryIsEqualToSomething() throws Exception {
        Apiary apiary;
        if (TestUtil.findAll(em, Apiary.class).isEmpty()) {
            beekeeperRepository.saveAndFlush(beekeeper);
            apiary = ApiaryResourceIT.createEntity(em);
        } else {
            apiary = TestUtil.findAll(em, Apiary.class).get(0);
        }
        em.persist(apiary);
        em.flush();
        beekeeper.addApiary(apiary);
        beekeeperRepository.saveAndFlush(beekeeper);
        Long apiaryId = apiary.getId();

        // Get all the beekeeperList where apiary equals to apiaryId
        defaultBeekeeperShouldBeFound("apiaryId.equals=" + apiaryId);

        // Get all the beekeeperList where apiary equals to (apiaryId + 1)
        defaultBeekeeperShouldNotBeFound("apiaryId.equals=" + (apiaryId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBeekeeperShouldBeFound(String filter) throws Exception {
        restBeekeeperMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(beekeeper.getId().intValue())))
            .andExpect(jsonPath("$.[*].beekeeperCompleteName").value(hasItem(DEFAULT_BEEKEEPER_COMPLETE_NAME)))
            .andExpect(jsonPath("$.[*].beekeeperNumber").value(hasItem(DEFAULT_BEEKEEPER_NUMBER)))
            .andExpect(jsonPath("$.[*].entityZoneResidence").value(hasItem(DEFAULT_ENTITY_ZONE_RESIDENCE)))
            .andExpect(jsonPath("$.[*].nif").value(hasItem(DEFAULT_NIF)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].faxNumber").value(hasItem(DEFAULT_FAX_NUMBER)));

        // Check, that the count call also returns 1
        restBeekeeperMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBeekeeperShouldNotBeFound(String filter) throws Exception {
        restBeekeeperMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBeekeeperMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingBeekeeper() throws Exception {
        // Get the beekeeper
        restBeekeeperMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBeekeeper() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        int databaseSizeBeforeUpdate = beekeeperRepository.findAll().size();

        // Update the beekeeper
        Beekeeper updatedBeekeeper = beekeeperRepository.findById(beekeeper.getId()).get();
        // Disconnect from session so that the updates on updatedBeekeeper are not directly saved in db
        em.detach(updatedBeekeeper);
        updatedBeekeeper
            .beekeeperCompleteName(UPDATED_BEEKEEPER_COMPLETE_NAME)
            .beekeeperNumber(UPDATED_BEEKEEPER_NUMBER)
            .entityZoneResidence(UPDATED_ENTITY_ZONE_RESIDENCE)
            .nif(UPDATED_NIF)
            .address(UPDATED_ADDRESS)
            .postalCode(UPDATED_POSTAL_CODE)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .faxNumber(UPDATED_FAX_NUMBER);
        BeekeeperDTO beekeeperDTO = beekeeperMapper.toDto(updatedBeekeeper);

        restBeekeeperMockMvc
            .perform(
                put(ENTITY_API_URL_ID, beekeeperDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(beekeeperDTO))
            )
            .andExpect(status().isOk());

        // Validate the Beekeeper in the database
        List<Beekeeper> beekeeperList = beekeeperRepository.findAll();
        assertThat(beekeeperList).hasSize(databaseSizeBeforeUpdate);
        Beekeeper testBeekeeper = beekeeperList.get(beekeeperList.size() - 1);
        assertThat(testBeekeeper.getBeekeeperCompleteName()).isEqualTo(UPDATED_BEEKEEPER_COMPLETE_NAME);
        assertThat(testBeekeeper.getBeekeeperNumber()).isEqualTo(UPDATED_BEEKEEPER_NUMBER);
        assertThat(testBeekeeper.getEntityZoneResidence()).isEqualTo(UPDATED_ENTITY_ZONE_RESIDENCE);
        assertThat(testBeekeeper.getNif()).isEqualTo(UPDATED_NIF);
        assertThat(testBeekeeper.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testBeekeeper.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testBeekeeper.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testBeekeeper.getFaxNumber()).isEqualTo(UPDATED_FAX_NUMBER);
    }

    @Test
    @Transactional
    void putNonExistingBeekeeper() throws Exception {
        int databaseSizeBeforeUpdate = beekeeperRepository.findAll().size();
        beekeeper.setId(count.incrementAndGet());

        // Create the Beekeeper
        BeekeeperDTO beekeeperDTO = beekeeperMapper.toDto(beekeeper);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBeekeeperMockMvc
            .perform(
                put(ENTITY_API_URL_ID, beekeeperDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(beekeeperDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Beekeeper in the database
        List<Beekeeper> beekeeperList = beekeeperRepository.findAll();
        assertThat(beekeeperList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBeekeeper() throws Exception {
        int databaseSizeBeforeUpdate = beekeeperRepository.findAll().size();
        beekeeper.setId(count.incrementAndGet());

        // Create the Beekeeper
        BeekeeperDTO beekeeperDTO = beekeeperMapper.toDto(beekeeper);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBeekeeperMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(beekeeperDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Beekeeper in the database
        List<Beekeeper> beekeeperList = beekeeperRepository.findAll();
        assertThat(beekeeperList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBeekeeper() throws Exception {
        int databaseSizeBeforeUpdate = beekeeperRepository.findAll().size();
        beekeeper.setId(count.incrementAndGet());

        // Create the Beekeeper
        BeekeeperDTO beekeeperDTO = beekeeperMapper.toDto(beekeeper);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBeekeeperMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(beekeeperDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Beekeeper in the database
        List<Beekeeper> beekeeperList = beekeeperRepository.findAll();
        assertThat(beekeeperList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBeekeeperWithPatch() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        int databaseSizeBeforeUpdate = beekeeperRepository.findAll().size();

        // Update the beekeeper using partial update
        Beekeeper partialUpdatedBeekeeper = new Beekeeper();
        partialUpdatedBeekeeper.setId(beekeeper.getId());

        partialUpdatedBeekeeper
            .beekeeperNumber(UPDATED_BEEKEEPER_NUMBER)
            .entityZoneResidence(UPDATED_ENTITY_ZONE_RESIDENCE)
            .nif(UPDATED_NIF)
            .address(UPDATED_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .faxNumber(UPDATED_FAX_NUMBER);

        restBeekeeperMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBeekeeper.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBeekeeper))
            )
            .andExpect(status().isOk());

        // Validate the Beekeeper in the database
        List<Beekeeper> beekeeperList = beekeeperRepository.findAll();
        assertThat(beekeeperList).hasSize(databaseSizeBeforeUpdate);
        Beekeeper testBeekeeper = beekeeperList.get(beekeeperList.size() - 1);
        assertThat(testBeekeeper.getBeekeeperCompleteName()).isEqualTo(DEFAULT_BEEKEEPER_COMPLETE_NAME);
        assertThat(testBeekeeper.getBeekeeperNumber()).isEqualTo(UPDATED_BEEKEEPER_NUMBER);
        assertThat(testBeekeeper.getEntityZoneResidence()).isEqualTo(UPDATED_ENTITY_ZONE_RESIDENCE);
        assertThat(testBeekeeper.getNif()).isEqualTo(UPDATED_NIF);
        assertThat(testBeekeeper.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testBeekeeper.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testBeekeeper.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testBeekeeper.getFaxNumber()).isEqualTo(UPDATED_FAX_NUMBER);
    }

    @Test
    @Transactional
    void fullUpdateBeekeeperWithPatch() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        int databaseSizeBeforeUpdate = beekeeperRepository.findAll().size();

        // Update the beekeeper using partial update
        Beekeeper partialUpdatedBeekeeper = new Beekeeper();
        partialUpdatedBeekeeper.setId(beekeeper.getId());

        partialUpdatedBeekeeper
            .beekeeperCompleteName(UPDATED_BEEKEEPER_COMPLETE_NAME)
            .beekeeperNumber(UPDATED_BEEKEEPER_NUMBER)
            .entityZoneResidence(UPDATED_ENTITY_ZONE_RESIDENCE)
            .nif(UPDATED_NIF)
            .address(UPDATED_ADDRESS)
            .postalCode(UPDATED_POSTAL_CODE)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .faxNumber(UPDATED_FAX_NUMBER);

        restBeekeeperMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBeekeeper.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBeekeeper))
            )
            .andExpect(status().isOk());

        // Validate the Beekeeper in the database
        List<Beekeeper> beekeeperList = beekeeperRepository.findAll();
        assertThat(beekeeperList).hasSize(databaseSizeBeforeUpdate);
        Beekeeper testBeekeeper = beekeeperList.get(beekeeperList.size() - 1);
        assertThat(testBeekeeper.getBeekeeperCompleteName()).isEqualTo(UPDATED_BEEKEEPER_COMPLETE_NAME);
        assertThat(testBeekeeper.getBeekeeperNumber()).isEqualTo(UPDATED_BEEKEEPER_NUMBER);
        assertThat(testBeekeeper.getEntityZoneResidence()).isEqualTo(UPDATED_ENTITY_ZONE_RESIDENCE);
        assertThat(testBeekeeper.getNif()).isEqualTo(UPDATED_NIF);
        assertThat(testBeekeeper.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testBeekeeper.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testBeekeeper.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testBeekeeper.getFaxNumber()).isEqualTo(UPDATED_FAX_NUMBER);
    }

    @Test
    @Transactional
    void patchNonExistingBeekeeper() throws Exception {
        int databaseSizeBeforeUpdate = beekeeperRepository.findAll().size();
        beekeeper.setId(count.incrementAndGet());

        // Create the Beekeeper
        BeekeeperDTO beekeeperDTO = beekeeperMapper.toDto(beekeeper);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBeekeeperMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, beekeeperDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(beekeeperDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Beekeeper in the database
        List<Beekeeper> beekeeperList = beekeeperRepository.findAll();
        assertThat(beekeeperList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBeekeeper() throws Exception {
        int databaseSizeBeforeUpdate = beekeeperRepository.findAll().size();
        beekeeper.setId(count.incrementAndGet());

        // Create the Beekeeper
        BeekeeperDTO beekeeperDTO = beekeeperMapper.toDto(beekeeper);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBeekeeperMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(beekeeperDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Beekeeper in the database
        List<Beekeeper> beekeeperList = beekeeperRepository.findAll();
        assertThat(beekeeperList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBeekeeper() throws Exception {
        int databaseSizeBeforeUpdate = beekeeperRepository.findAll().size();
        beekeeper.setId(count.incrementAndGet());

        // Create the Beekeeper
        BeekeeperDTO beekeeperDTO = beekeeperMapper.toDto(beekeeper);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBeekeeperMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(beekeeperDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Beekeeper in the database
        List<Beekeeper> beekeeperList = beekeeperRepository.findAll();
        assertThat(beekeeperList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBeekeeper() throws Exception {
        // Initialize the database
        beekeeperRepository.saveAndFlush(beekeeper);

        int databaseSizeBeforeDelete = beekeeperRepository.findAll().size();

        // Delete the beekeeper
        restBeekeeperMockMvc
            .perform(delete(ENTITY_API_URL_ID, beekeeper.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Beekeeper> beekeeperList = beekeeperRepository.findAll();
        assertThat(beekeeperList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
