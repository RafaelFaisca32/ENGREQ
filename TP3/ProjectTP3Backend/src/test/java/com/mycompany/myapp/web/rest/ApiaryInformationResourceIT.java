package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.AnnualInventoryDeclaration;
import com.mycompany.myapp.domain.ApiaryInformation;
import com.mycompany.myapp.repository.ApiaryInformationRepository;
import com.mycompany.myapp.service.criteria.ApiaryInformationCriteria;
import com.mycompany.myapp.service.dto.ApiaryInformationDTO;
import com.mycompany.myapp.service.mapper.ApiaryInformationMapper;
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
 * Integration tests for the {@link ApiaryInformationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ApiaryInformationResourceIT {

    private static final Integer DEFAULT_ZONE_NUMBER = 1;
    private static final Integer UPDATED_ZONE_NUMBER = 2;
    private static final Integer SMALLER_ZONE_NUMBER = 1 - 1;

    private static final String DEFAULT_ZONE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ZONE_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_HIVES = 1;
    private static final Integer UPDATED_NUMBER_HIVES = 2;
    private static final Integer SMALLER_NUMBER_HIVES = 1 - 1;

    private static final Boolean DEFAULT_INTENSIVE = false;
    private static final Boolean UPDATED_INTENSIVE = true;

    private static final Boolean DEFAULT_TRANSHUMANCE = false;
    private static final Boolean UPDATED_TRANSHUMANCE = true;

    private static final String DEFAULT_COORD_X = "AAAAAAAAAA";
    private static final String UPDATED_COORD_X = "BBBBBBBBBB";

    private static final String DEFAULT_COORD_Y = "AAAAAAAAAA";
    private static final String UPDATED_COORD_Y = "BBBBBBBBBB";

    private static final String DEFAULT_COORD_Z = "AAAAAAAAAA";
    private static final String UPDATED_COORD_Z = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_FRAMES = 1;
    private static final Integer UPDATED_NUMBER_FRAMES = 2;
    private static final Integer SMALLER_NUMBER_FRAMES = 1 - 1;

    private static final String ENTITY_API_URL = "/api/apiary-informations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ApiaryInformationRepository apiaryInformationRepository;

    @Autowired
    private ApiaryInformationMapper apiaryInformationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restApiaryInformationMockMvc;

    private ApiaryInformation apiaryInformation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApiaryInformation createEntity(EntityManager em) {
        ApiaryInformation apiaryInformation = new ApiaryInformation()
            .zoneNumber(DEFAULT_ZONE_NUMBER)
            .zoneName(DEFAULT_ZONE_NAME)
            .numberHives(DEFAULT_NUMBER_HIVES)
            .intensive(DEFAULT_INTENSIVE)
            .transhumance(DEFAULT_TRANSHUMANCE)
            .coordX(DEFAULT_COORD_X)
            .coordY(DEFAULT_COORD_Y)
            .coordZ(DEFAULT_COORD_Z)
            .numberFrames(DEFAULT_NUMBER_FRAMES);
        return apiaryInformation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApiaryInformation createUpdatedEntity(EntityManager em) {
        ApiaryInformation apiaryInformation = new ApiaryInformation()
            .zoneNumber(UPDATED_ZONE_NUMBER)
            .zoneName(UPDATED_ZONE_NAME)
            .numberHives(UPDATED_NUMBER_HIVES)
            .intensive(UPDATED_INTENSIVE)
            .transhumance(UPDATED_TRANSHUMANCE)
            .coordX(UPDATED_COORD_X)
            .coordY(UPDATED_COORD_Y)
            .coordZ(UPDATED_COORD_Z)
            .numberFrames(UPDATED_NUMBER_FRAMES);
        return apiaryInformation;
    }

    @BeforeEach
    public void initTest() {
        apiaryInformation = createEntity(em);
    }

    @Test
    @Transactional
    void createApiaryInformation() throws Exception {
        int databaseSizeBeforeCreate = apiaryInformationRepository.findAll().size();
        // Create the ApiaryInformation
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);
        restApiaryInformationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ApiaryInformation in the database
        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeCreate + 1);
        ApiaryInformation testApiaryInformation = apiaryInformationList.get(apiaryInformationList.size() - 1);
        assertThat(testApiaryInformation.getZoneNumber()).isEqualTo(DEFAULT_ZONE_NUMBER);
        assertThat(testApiaryInformation.getZoneName()).isEqualTo(DEFAULT_ZONE_NAME);
        assertThat(testApiaryInformation.getNumberHives()).isEqualTo(DEFAULT_NUMBER_HIVES);
        assertThat(testApiaryInformation.getIntensive()).isEqualTo(DEFAULT_INTENSIVE);
        assertThat(testApiaryInformation.getTranshumance()).isEqualTo(DEFAULT_TRANSHUMANCE);
        assertThat(testApiaryInformation.getCoordX()).isEqualTo(DEFAULT_COORD_X);
        assertThat(testApiaryInformation.getCoordY()).isEqualTo(DEFAULT_COORD_Y);
        assertThat(testApiaryInformation.getCoordZ()).isEqualTo(DEFAULT_COORD_Z);
        assertThat(testApiaryInformation.getNumberFrames()).isEqualTo(DEFAULT_NUMBER_FRAMES);
    }

    @Test
    @Transactional
    void createApiaryInformationWithExistingId() throws Exception {
        // Create the ApiaryInformation with an existing ID
        apiaryInformation.setId(1L);
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        int databaseSizeBeforeCreate = apiaryInformationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restApiaryInformationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ApiaryInformation in the database
        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkZoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryInformationRepository.findAll().size();
        // set the field null
        apiaryInformation.setZoneNumber(null);

        // Create the ApiaryInformation, which fails.
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        restApiaryInformationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isBadRequest());

        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkZoneNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryInformationRepository.findAll().size();
        // set the field null
        apiaryInformation.setZoneName(null);

        // Create the ApiaryInformation, which fails.
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        restApiaryInformationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isBadRequest());

        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNumberHivesIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryInformationRepository.findAll().size();
        // set the field null
        apiaryInformation.setNumberHives(null);

        // Create the ApiaryInformation, which fails.
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        restApiaryInformationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isBadRequest());

        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIntensiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryInformationRepository.findAll().size();
        // set the field null
        apiaryInformation.setIntensive(null);

        // Create the ApiaryInformation, which fails.
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        restApiaryInformationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isBadRequest());

        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTranshumanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryInformationRepository.findAll().size();
        // set the field null
        apiaryInformation.setTranshumance(null);

        // Create the ApiaryInformation, which fails.
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        restApiaryInformationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isBadRequest());

        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCoordXIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryInformationRepository.findAll().size();
        // set the field null
        apiaryInformation.setCoordX(null);

        // Create the ApiaryInformation, which fails.
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        restApiaryInformationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isBadRequest());

        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCoordYIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryInformationRepository.findAll().size();
        // set the field null
        apiaryInformation.setCoordY(null);

        // Create the ApiaryInformation, which fails.
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        restApiaryInformationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isBadRequest());

        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCoordZIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryInformationRepository.findAll().size();
        // set the field null
        apiaryInformation.setCoordZ(null);

        // Create the ApiaryInformation, which fails.
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        restApiaryInformationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isBadRequest());

        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNumberFramesIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryInformationRepository.findAll().size();
        // set the field null
        apiaryInformation.setNumberFrames(null);

        // Create the ApiaryInformation, which fails.
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        restApiaryInformationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isBadRequest());

        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllApiaryInformations() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList
        restApiaryInformationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apiaryInformation.getId().intValue())))
            .andExpect(jsonPath("$.[*].zoneNumber").value(hasItem(DEFAULT_ZONE_NUMBER)))
            .andExpect(jsonPath("$.[*].zoneName").value(hasItem(DEFAULT_ZONE_NAME)))
            .andExpect(jsonPath("$.[*].numberHives").value(hasItem(DEFAULT_NUMBER_HIVES)))
            .andExpect(jsonPath("$.[*].intensive").value(hasItem(DEFAULT_INTENSIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].transhumance").value(hasItem(DEFAULT_TRANSHUMANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].coordX").value(hasItem(DEFAULT_COORD_X)))
            .andExpect(jsonPath("$.[*].coordY").value(hasItem(DEFAULT_COORD_Y)))
            .andExpect(jsonPath("$.[*].coordZ").value(hasItem(DEFAULT_COORD_Z)))
            .andExpect(jsonPath("$.[*].numberFrames").value(hasItem(DEFAULT_NUMBER_FRAMES)));
    }

    @Test
    @Transactional
    void getApiaryInformation() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get the apiaryInformation
        restApiaryInformationMockMvc
            .perform(get(ENTITY_API_URL_ID, apiaryInformation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(apiaryInformation.getId().intValue()))
            .andExpect(jsonPath("$.zoneNumber").value(DEFAULT_ZONE_NUMBER))
            .andExpect(jsonPath("$.zoneName").value(DEFAULT_ZONE_NAME))
            .andExpect(jsonPath("$.numberHives").value(DEFAULT_NUMBER_HIVES))
            .andExpect(jsonPath("$.intensive").value(DEFAULT_INTENSIVE.booleanValue()))
            .andExpect(jsonPath("$.transhumance").value(DEFAULT_TRANSHUMANCE.booleanValue()))
            .andExpect(jsonPath("$.coordX").value(DEFAULT_COORD_X))
            .andExpect(jsonPath("$.coordY").value(DEFAULT_COORD_Y))
            .andExpect(jsonPath("$.coordZ").value(DEFAULT_COORD_Z))
            .andExpect(jsonPath("$.numberFrames").value(DEFAULT_NUMBER_FRAMES));
    }

    @Test
    @Transactional
    void getApiaryInformationsByIdFiltering() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        Long id = apiaryInformation.getId();

        defaultApiaryInformationShouldBeFound("id.equals=" + id);
        defaultApiaryInformationShouldNotBeFound("id.notEquals=" + id);

        defaultApiaryInformationShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultApiaryInformationShouldNotBeFound("id.greaterThan=" + id);

        defaultApiaryInformationShouldBeFound("id.lessThanOrEqual=" + id);
        defaultApiaryInformationShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByZoneNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where zoneNumber equals to DEFAULT_ZONE_NUMBER
        defaultApiaryInformationShouldBeFound("zoneNumber.equals=" + DEFAULT_ZONE_NUMBER);

        // Get all the apiaryInformationList where zoneNumber equals to UPDATED_ZONE_NUMBER
        defaultApiaryInformationShouldNotBeFound("zoneNumber.equals=" + UPDATED_ZONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByZoneNumberIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where zoneNumber in DEFAULT_ZONE_NUMBER or UPDATED_ZONE_NUMBER
        defaultApiaryInformationShouldBeFound("zoneNumber.in=" + DEFAULT_ZONE_NUMBER + "," + UPDATED_ZONE_NUMBER);

        // Get all the apiaryInformationList where zoneNumber equals to UPDATED_ZONE_NUMBER
        defaultApiaryInformationShouldNotBeFound("zoneNumber.in=" + UPDATED_ZONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByZoneNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where zoneNumber is not null
        defaultApiaryInformationShouldBeFound("zoneNumber.specified=true");

        // Get all the apiaryInformationList where zoneNumber is null
        defaultApiaryInformationShouldNotBeFound("zoneNumber.specified=false");
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByZoneNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where zoneNumber is greater than or equal to DEFAULT_ZONE_NUMBER
        defaultApiaryInformationShouldBeFound("zoneNumber.greaterThanOrEqual=" + DEFAULT_ZONE_NUMBER);

        // Get all the apiaryInformationList where zoneNumber is greater than or equal to UPDATED_ZONE_NUMBER
        defaultApiaryInformationShouldNotBeFound("zoneNumber.greaterThanOrEqual=" + UPDATED_ZONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByZoneNumberIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where zoneNumber is less than or equal to DEFAULT_ZONE_NUMBER
        defaultApiaryInformationShouldBeFound("zoneNumber.lessThanOrEqual=" + DEFAULT_ZONE_NUMBER);

        // Get all the apiaryInformationList where zoneNumber is less than or equal to SMALLER_ZONE_NUMBER
        defaultApiaryInformationShouldNotBeFound("zoneNumber.lessThanOrEqual=" + SMALLER_ZONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByZoneNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where zoneNumber is less than DEFAULT_ZONE_NUMBER
        defaultApiaryInformationShouldNotBeFound("zoneNumber.lessThan=" + DEFAULT_ZONE_NUMBER);

        // Get all the apiaryInformationList where zoneNumber is less than UPDATED_ZONE_NUMBER
        defaultApiaryInformationShouldBeFound("zoneNumber.lessThan=" + UPDATED_ZONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByZoneNumberIsGreaterThanSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where zoneNumber is greater than DEFAULT_ZONE_NUMBER
        defaultApiaryInformationShouldNotBeFound("zoneNumber.greaterThan=" + DEFAULT_ZONE_NUMBER);

        // Get all the apiaryInformationList where zoneNumber is greater than SMALLER_ZONE_NUMBER
        defaultApiaryInformationShouldBeFound("zoneNumber.greaterThan=" + SMALLER_ZONE_NUMBER);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByZoneNameIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where zoneName equals to DEFAULT_ZONE_NAME
        defaultApiaryInformationShouldBeFound("zoneName.equals=" + DEFAULT_ZONE_NAME);

        // Get all the apiaryInformationList where zoneName equals to UPDATED_ZONE_NAME
        defaultApiaryInformationShouldNotBeFound("zoneName.equals=" + UPDATED_ZONE_NAME);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByZoneNameIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where zoneName in DEFAULT_ZONE_NAME or UPDATED_ZONE_NAME
        defaultApiaryInformationShouldBeFound("zoneName.in=" + DEFAULT_ZONE_NAME + "," + UPDATED_ZONE_NAME);

        // Get all the apiaryInformationList where zoneName equals to UPDATED_ZONE_NAME
        defaultApiaryInformationShouldNotBeFound("zoneName.in=" + UPDATED_ZONE_NAME);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByZoneNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where zoneName is not null
        defaultApiaryInformationShouldBeFound("zoneName.specified=true");

        // Get all the apiaryInformationList where zoneName is null
        defaultApiaryInformationShouldNotBeFound("zoneName.specified=false");
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByZoneNameContainsSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where zoneName contains DEFAULT_ZONE_NAME
        defaultApiaryInformationShouldBeFound("zoneName.contains=" + DEFAULT_ZONE_NAME);

        // Get all the apiaryInformationList where zoneName contains UPDATED_ZONE_NAME
        defaultApiaryInformationShouldNotBeFound("zoneName.contains=" + UPDATED_ZONE_NAME);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByZoneNameNotContainsSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where zoneName does not contain DEFAULT_ZONE_NAME
        defaultApiaryInformationShouldNotBeFound("zoneName.doesNotContain=" + DEFAULT_ZONE_NAME);

        // Get all the apiaryInformationList where zoneName does not contain UPDATED_ZONE_NAME
        defaultApiaryInformationShouldBeFound("zoneName.doesNotContain=" + UPDATED_ZONE_NAME);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByNumberHivesIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where numberHives equals to DEFAULT_NUMBER_HIVES
        defaultApiaryInformationShouldBeFound("numberHives.equals=" + DEFAULT_NUMBER_HIVES);

        // Get all the apiaryInformationList where numberHives equals to UPDATED_NUMBER_HIVES
        defaultApiaryInformationShouldNotBeFound("numberHives.equals=" + UPDATED_NUMBER_HIVES);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByNumberHivesIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where numberHives in DEFAULT_NUMBER_HIVES or UPDATED_NUMBER_HIVES
        defaultApiaryInformationShouldBeFound("numberHives.in=" + DEFAULT_NUMBER_HIVES + "," + UPDATED_NUMBER_HIVES);

        // Get all the apiaryInformationList where numberHives equals to UPDATED_NUMBER_HIVES
        defaultApiaryInformationShouldNotBeFound("numberHives.in=" + UPDATED_NUMBER_HIVES);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByNumberHivesIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where numberHives is not null
        defaultApiaryInformationShouldBeFound("numberHives.specified=true");

        // Get all the apiaryInformationList where numberHives is null
        defaultApiaryInformationShouldNotBeFound("numberHives.specified=false");
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByNumberHivesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where numberHives is greater than or equal to DEFAULT_NUMBER_HIVES
        defaultApiaryInformationShouldBeFound("numberHives.greaterThanOrEqual=" + DEFAULT_NUMBER_HIVES);

        // Get all the apiaryInformationList where numberHives is greater than or equal to UPDATED_NUMBER_HIVES
        defaultApiaryInformationShouldNotBeFound("numberHives.greaterThanOrEqual=" + UPDATED_NUMBER_HIVES);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByNumberHivesIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where numberHives is less than or equal to DEFAULT_NUMBER_HIVES
        defaultApiaryInformationShouldBeFound("numberHives.lessThanOrEqual=" + DEFAULT_NUMBER_HIVES);

        // Get all the apiaryInformationList where numberHives is less than or equal to SMALLER_NUMBER_HIVES
        defaultApiaryInformationShouldNotBeFound("numberHives.lessThanOrEqual=" + SMALLER_NUMBER_HIVES);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByNumberHivesIsLessThanSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where numberHives is less than DEFAULT_NUMBER_HIVES
        defaultApiaryInformationShouldNotBeFound("numberHives.lessThan=" + DEFAULT_NUMBER_HIVES);

        // Get all the apiaryInformationList where numberHives is less than UPDATED_NUMBER_HIVES
        defaultApiaryInformationShouldBeFound("numberHives.lessThan=" + UPDATED_NUMBER_HIVES);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByNumberHivesIsGreaterThanSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where numberHives is greater than DEFAULT_NUMBER_HIVES
        defaultApiaryInformationShouldNotBeFound("numberHives.greaterThan=" + DEFAULT_NUMBER_HIVES);

        // Get all the apiaryInformationList where numberHives is greater than SMALLER_NUMBER_HIVES
        defaultApiaryInformationShouldBeFound("numberHives.greaterThan=" + SMALLER_NUMBER_HIVES);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByIntensiveIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where intensive equals to DEFAULT_INTENSIVE
        defaultApiaryInformationShouldBeFound("intensive.equals=" + DEFAULT_INTENSIVE);

        // Get all the apiaryInformationList where intensive equals to UPDATED_INTENSIVE
        defaultApiaryInformationShouldNotBeFound("intensive.equals=" + UPDATED_INTENSIVE);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByIntensiveIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where intensive in DEFAULT_INTENSIVE or UPDATED_INTENSIVE
        defaultApiaryInformationShouldBeFound("intensive.in=" + DEFAULT_INTENSIVE + "," + UPDATED_INTENSIVE);

        // Get all the apiaryInformationList where intensive equals to UPDATED_INTENSIVE
        defaultApiaryInformationShouldNotBeFound("intensive.in=" + UPDATED_INTENSIVE);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByIntensiveIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where intensive is not null
        defaultApiaryInformationShouldBeFound("intensive.specified=true");

        // Get all the apiaryInformationList where intensive is null
        defaultApiaryInformationShouldNotBeFound("intensive.specified=false");
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByTranshumanceIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where transhumance equals to DEFAULT_TRANSHUMANCE
        defaultApiaryInformationShouldBeFound("transhumance.equals=" + DEFAULT_TRANSHUMANCE);

        // Get all the apiaryInformationList where transhumance equals to UPDATED_TRANSHUMANCE
        defaultApiaryInformationShouldNotBeFound("transhumance.equals=" + UPDATED_TRANSHUMANCE);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByTranshumanceIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where transhumance in DEFAULT_TRANSHUMANCE or UPDATED_TRANSHUMANCE
        defaultApiaryInformationShouldBeFound("transhumance.in=" + DEFAULT_TRANSHUMANCE + "," + UPDATED_TRANSHUMANCE);

        // Get all the apiaryInformationList where transhumance equals to UPDATED_TRANSHUMANCE
        defaultApiaryInformationShouldNotBeFound("transhumance.in=" + UPDATED_TRANSHUMANCE);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByTranshumanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where transhumance is not null
        defaultApiaryInformationShouldBeFound("transhumance.specified=true");

        // Get all the apiaryInformationList where transhumance is null
        defaultApiaryInformationShouldNotBeFound("transhumance.specified=false");
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordXIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordX equals to DEFAULT_COORD_X
        defaultApiaryInformationShouldBeFound("coordX.equals=" + DEFAULT_COORD_X);

        // Get all the apiaryInformationList where coordX equals to UPDATED_COORD_X
        defaultApiaryInformationShouldNotBeFound("coordX.equals=" + UPDATED_COORD_X);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordXIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordX in DEFAULT_COORD_X or UPDATED_COORD_X
        defaultApiaryInformationShouldBeFound("coordX.in=" + DEFAULT_COORD_X + "," + UPDATED_COORD_X);

        // Get all the apiaryInformationList where coordX equals to UPDATED_COORD_X
        defaultApiaryInformationShouldNotBeFound("coordX.in=" + UPDATED_COORD_X);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordXIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordX is not null
        defaultApiaryInformationShouldBeFound("coordX.specified=true");

        // Get all the apiaryInformationList where coordX is null
        defaultApiaryInformationShouldNotBeFound("coordX.specified=false");
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordXContainsSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordX contains DEFAULT_COORD_X
        defaultApiaryInformationShouldBeFound("coordX.contains=" + DEFAULT_COORD_X);

        // Get all the apiaryInformationList where coordX contains UPDATED_COORD_X
        defaultApiaryInformationShouldNotBeFound("coordX.contains=" + UPDATED_COORD_X);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordXNotContainsSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordX does not contain DEFAULT_COORD_X
        defaultApiaryInformationShouldNotBeFound("coordX.doesNotContain=" + DEFAULT_COORD_X);

        // Get all the apiaryInformationList where coordX does not contain UPDATED_COORD_X
        defaultApiaryInformationShouldBeFound("coordX.doesNotContain=" + UPDATED_COORD_X);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordYIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordY equals to DEFAULT_COORD_Y
        defaultApiaryInformationShouldBeFound("coordY.equals=" + DEFAULT_COORD_Y);

        // Get all the apiaryInformationList where coordY equals to UPDATED_COORD_Y
        defaultApiaryInformationShouldNotBeFound("coordY.equals=" + UPDATED_COORD_Y);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordYIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordY in DEFAULT_COORD_Y or UPDATED_COORD_Y
        defaultApiaryInformationShouldBeFound("coordY.in=" + DEFAULT_COORD_Y + "," + UPDATED_COORD_Y);

        // Get all the apiaryInformationList where coordY equals to UPDATED_COORD_Y
        defaultApiaryInformationShouldNotBeFound("coordY.in=" + UPDATED_COORD_Y);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordYIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordY is not null
        defaultApiaryInformationShouldBeFound("coordY.specified=true");

        // Get all the apiaryInformationList where coordY is null
        defaultApiaryInformationShouldNotBeFound("coordY.specified=false");
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordYContainsSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordY contains DEFAULT_COORD_Y
        defaultApiaryInformationShouldBeFound("coordY.contains=" + DEFAULT_COORD_Y);

        // Get all the apiaryInformationList where coordY contains UPDATED_COORD_Y
        defaultApiaryInformationShouldNotBeFound("coordY.contains=" + UPDATED_COORD_Y);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordYNotContainsSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordY does not contain DEFAULT_COORD_Y
        defaultApiaryInformationShouldNotBeFound("coordY.doesNotContain=" + DEFAULT_COORD_Y);

        // Get all the apiaryInformationList where coordY does not contain UPDATED_COORD_Y
        defaultApiaryInformationShouldBeFound("coordY.doesNotContain=" + UPDATED_COORD_Y);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordZIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordZ equals to DEFAULT_COORD_Z
        defaultApiaryInformationShouldBeFound("coordZ.equals=" + DEFAULT_COORD_Z);

        // Get all the apiaryInformationList where coordZ equals to UPDATED_COORD_Z
        defaultApiaryInformationShouldNotBeFound("coordZ.equals=" + UPDATED_COORD_Z);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordZIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordZ in DEFAULT_COORD_Z or UPDATED_COORD_Z
        defaultApiaryInformationShouldBeFound("coordZ.in=" + DEFAULT_COORD_Z + "," + UPDATED_COORD_Z);

        // Get all the apiaryInformationList where coordZ equals to UPDATED_COORD_Z
        defaultApiaryInformationShouldNotBeFound("coordZ.in=" + UPDATED_COORD_Z);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordZIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordZ is not null
        defaultApiaryInformationShouldBeFound("coordZ.specified=true");

        // Get all the apiaryInformationList where coordZ is null
        defaultApiaryInformationShouldNotBeFound("coordZ.specified=false");
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordZContainsSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordZ contains DEFAULT_COORD_Z
        defaultApiaryInformationShouldBeFound("coordZ.contains=" + DEFAULT_COORD_Z);

        // Get all the apiaryInformationList where coordZ contains UPDATED_COORD_Z
        defaultApiaryInformationShouldNotBeFound("coordZ.contains=" + UPDATED_COORD_Z);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByCoordZNotContainsSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where coordZ does not contain DEFAULT_COORD_Z
        defaultApiaryInformationShouldNotBeFound("coordZ.doesNotContain=" + DEFAULT_COORD_Z);

        // Get all the apiaryInformationList where coordZ does not contain UPDATED_COORD_Z
        defaultApiaryInformationShouldBeFound("coordZ.doesNotContain=" + UPDATED_COORD_Z);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByNumberFramesIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where numberFrames equals to DEFAULT_NUMBER_FRAMES
        defaultApiaryInformationShouldBeFound("numberFrames.equals=" + DEFAULT_NUMBER_FRAMES);

        // Get all the apiaryInformationList where numberFrames equals to UPDATED_NUMBER_FRAMES
        defaultApiaryInformationShouldNotBeFound("numberFrames.equals=" + UPDATED_NUMBER_FRAMES);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByNumberFramesIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where numberFrames in DEFAULT_NUMBER_FRAMES or UPDATED_NUMBER_FRAMES
        defaultApiaryInformationShouldBeFound("numberFrames.in=" + DEFAULT_NUMBER_FRAMES + "," + UPDATED_NUMBER_FRAMES);

        // Get all the apiaryInformationList where numberFrames equals to UPDATED_NUMBER_FRAMES
        defaultApiaryInformationShouldNotBeFound("numberFrames.in=" + UPDATED_NUMBER_FRAMES);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByNumberFramesIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where numberFrames is not null
        defaultApiaryInformationShouldBeFound("numberFrames.specified=true");

        // Get all the apiaryInformationList where numberFrames is null
        defaultApiaryInformationShouldNotBeFound("numberFrames.specified=false");
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByNumberFramesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where numberFrames is greater than or equal to DEFAULT_NUMBER_FRAMES
        defaultApiaryInformationShouldBeFound("numberFrames.greaterThanOrEqual=" + DEFAULT_NUMBER_FRAMES);

        // Get all the apiaryInformationList where numberFrames is greater than or equal to UPDATED_NUMBER_FRAMES
        defaultApiaryInformationShouldNotBeFound("numberFrames.greaterThanOrEqual=" + UPDATED_NUMBER_FRAMES);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByNumberFramesIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where numberFrames is less than or equal to DEFAULT_NUMBER_FRAMES
        defaultApiaryInformationShouldBeFound("numberFrames.lessThanOrEqual=" + DEFAULT_NUMBER_FRAMES);

        // Get all the apiaryInformationList where numberFrames is less than or equal to SMALLER_NUMBER_FRAMES
        defaultApiaryInformationShouldNotBeFound("numberFrames.lessThanOrEqual=" + SMALLER_NUMBER_FRAMES);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByNumberFramesIsLessThanSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where numberFrames is less than DEFAULT_NUMBER_FRAMES
        defaultApiaryInformationShouldNotBeFound("numberFrames.lessThan=" + DEFAULT_NUMBER_FRAMES);

        // Get all the apiaryInformationList where numberFrames is less than UPDATED_NUMBER_FRAMES
        defaultApiaryInformationShouldBeFound("numberFrames.lessThan=" + UPDATED_NUMBER_FRAMES);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByNumberFramesIsGreaterThanSomething() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        // Get all the apiaryInformationList where numberFrames is greater than DEFAULT_NUMBER_FRAMES
        defaultApiaryInformationShouldNotBeFound("numberFrames.greaterThan=" + DEFAULT_NUMBER_FRAMES);

        // Get all the apiaryInformationList where numberFrames is greater than SMALLER_NUMBER_FRAMES
        defaultApiaryInformationShouldBeFound("numberFrames.greaterThan=" + SMALLER_NUMBER_FRAMES);
    }

    @Test
    @Transactional
    void getAllApiaryInformationsByAnnualInventoryDeclarationIsEqualToSomething() throws Exception {
        AnnualInventoryDeclaration annualInventoryDeclaration;
        if (TestUtil.findAll(em, AnnualInventoryDeclaration.class).isEmpty()) {
            apiaryInformationRepository.saveAndFlush(apiaryInformation);
            annualInventoryDeclaration = AnnualInventoryDeclarationResourceIT.createEntity(em);
        } else {
            annualInventoryDeclaration = TestUtil.findAll(em, AnnualInventoryDeclaration.class).get(0);
        }
        em.persist(annualInventoryDeclaration);
        em.flush();
        apiaryInformation.setAnnualInventoryDeclaration(annualInventoryDeclaration);
        apiaryInformationRepository.saveAndFlush(apiaryInformation);
        Long annualInventoryDeclarationId = annualInventoryDeclaration.getId();

        // Get all the apiaryInformationList where annualInventoryDeclaration equals to annualInventoryDeclarationId
        defaultApiaryInformationShouldBeFound("annualInventoryDeclarationId.equals=" + annualInventoryDeclarationId);

        // Get all the apiaryInformationList where annualInventoryDeclaration equals to (annualInventoryDeclarationId + 1)
        defaultApiaryInformationShouldNotBeFound("annualInventoryDeclarationId.equals=" + (annualInventoryDeclarationId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultApiaryInformationShouldBeFound(String filter) throws Exception {
        restApiaryInformationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apiaryInformation.getId().intValue())))
            .andExpect(jsonPath("$.[*].zoneNumber").value(hasItem(DEFAULT_ZONE_NUMBER)))
            .andExpect(jsonPath("$.[*].zoneName").value(hasItem(DEFAULT_ZONE_NAME)))
            .andExpect(jsonPath("$.[*].numberHives").value(hasItem(DEFAULT_NUMBER_HIVES)))
            .andExpect(jsonPath("$.[*].intensive").value(hasItem(DEFAULT_INTENSIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].transhumance").value(hasItem(DEFAULT_TRANSHUMANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].coordX").value(hasItem(DEFAULT_COORD_X)))
            .andExpect(jsonPath("$.[*].coordY").value(hasItem(DEFAULT_COORD_Y)))
            .andExpect(jsonPath("$.[*].coordZ").value(hasItem(DEFAULT_COORD_Z)))
            .andExpect(jsonPath("$.[*].numberFrames").value(hasItem(DEFAULT_NUMBER_FRAMES)));

        // Check, that the count call also returns 1
        restApiaryInformationMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultApiaryInformationShouldNotBeFound(String filter) throws Exception {
        restApiaryInformationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restApiaryInformationMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingApiaryInformation() throws Exception {
        // Get the apiaryInformation
        restApiaryInformationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingApiaryInformation() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        int databaseSizeBeforeUpdate = apiaryInformationRepository.findAll().size();

        // Update the apiaryInformation
        ApiaryInformation updatedApiaryInformation = apiaryInformationRepository.findById(apiaryInformation.getId()).get();
        // Disconnect from session so that the updates on updatedApiaryInformation are not directly saved in db
        em.detach(updatedApiaryInformation);
        updatedApiaryInformation
            .zoneNumber(UPDATED_ZONE_NUMBER)
            .zoneName(UPDATED_ZONE_NAME)
            .numberHives(UPDATED_NUMBER_HIVES)
            .intensive(UPDATED_INTENSIVE)
            .transhumance(UPDATED_TRANSHUMANCE)
            .coordX(UPDATED_COORD_X)
            .coordY(UPDATED_COORD_Y)
            .coordZ(UPDATED_COORD_Z)
            .numberFrames(UPDATED_NUMBER_FRAMES);
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(updatedApiaryInformation);

        restApiaryInformationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, apiaryInformationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isOk());

        // Validate the ApiaryInformation in the database
        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeUpdate);
        ApiaryInformation testApiaryInformation = apiaryInformationList.get(apiaryInformationList.size() - 1);
        assertThat(testApiaryInformation.getZoneNumber()).isEqualTo(UPDATED_ZONE_NUMBER);
        assertThat(testApiaryInformation.getZoneName()).isEqualTo(UPDATED_ZONE_NAME);
        assertThat(testApiaryInformation.getNumberHives()).isEqualTo(UPDATED_NUMBER_HIVES);
        assertThat(testApiaryInformation.getIntensive()).isEqualTo(UPDATED_INTENSIVE);
        assertThat(testApiaryInformation.getTranshumance()).isEqualTo(UPDATED_TRANSHUMANCE);
        assertThat(testApiaryInformation.getCoordX()).isEqualTo(UPDATED_COORD_X);
        assertThat(testApiaryInformation.getCoordY()).isEqualTo(UPDATED_COORD_Y);
        assertThat(testApiaryInformation.getCoordZ()).isEqualTo(UPDATED_COORD_Z);
        assertThat(testApiaryInformation.getNumberFrames()).isEqualTo(UPDATED_NUMBER_FRAMES);
    }

    @Test
    @Transactional
    void putNonExistingApiaryInformation() throws Exception {
        int databaseSizeBeforeUpdate = apiaryInformationRepository.findAll().size();
        apiaryInformation.setId(count.incrementAndGet());

        // Create the ApiaryInformation
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApiaryInformationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, apiaryInformationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ApiaryInformation in the database
        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchApiaryInformation() throws Exception {
        int databaseSizeBeforeUpdate = apiaryInformationRepository.findAll().size();
        apiaryInformation.setId(count.incrementAndGet());

        // Create the ApiaryInformation
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApiaryInformationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ApiaryInformation in the database
        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamApiaryInformation() throws Exception {
        int databaseSizeBeforeUpdate = apiaryInformationRepository.findAll().size();
        apiaryInformation.setId(count.incrementAndGet());

        // Create the ApiaryInformation
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApiaryInformationMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ApiaryInformation in the database
        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateApiaryInformationWithPatch() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        int databaseSizeBeforeUpdate = apiaryInformationRepository.findAll().size();

        // Update the apiaryInformation using partial update
        ApiaryInformation partialUpdatedApiaryInformation = new ApiaryInformation();
        partialUpdatedApiaryInformation.setId(apiaryInformation.getId());

        partialUpdatedApiaryInformation.numberHives(UPDATED_NUMBER_HIVES).transhumance(UPDATED_TRANSHUMANCE).coordY(UPDATED_COORD_Y);

        restApiaryInformationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedApiaryInformation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedApiaryInformation))
            )
            .andExpect(status().isOk());

        // Validate the ApiaryInformation in the database
        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeUpdate);
        ApiaryInformation testApiaryInformation = apiaryInformationList.get(apiaryInformationList.size() - 1);
        assertThat(testApiaryInformation.getZoneNumber()).isEqualTo(DEFAULT_ZONE_NUMBER);
        assertThat(testApiaryInformation.getZoneName()).isEqualTo(DEFAULT_ZONE_NAME);
        assertThat(testApiaryInformation.getNumberHives()).isEqualTo(UPDATED_NUMBER_HIVES);
        assertThat(testApiaryInformation.getIntensive()).isEqualTo(DEFAULT_INTENSIVE);
        assertThat(testApiaryInformation.getTranshumance()).isEqualTo(UPDATED_TRANSHUMANCE);
        assertThat(testApiaryInformation.getCoordX()).isEqualTo(DEFAULT_COORD_X);
        assertThat(testApiaryInformation.getCoordY()).isEqualTo(UPDATED_COORD_Y);
        assertThat(testApiaryInformation.getCoordZ()).isEqualTo(DEFAULT_COORD_Z);
        assertThat(testApiaryInformation.getNumberFrames()).isEqualTo(DEFAULT_NUMBER_FRAMES);
    }

    @Test
    @Transactional
    void fullUpdateApiaryInformationWithPatch() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        int databaseSizeBeforeUpdate = apiaryInformationRepository.findAll().size();

        // Update the apiaryInformation using partial update
        ApiaryInformation partialUpdatedApiaryInformation = new ApiaryInformation();
        partialUpdatedApiaryInformation.setId(apiaryInformation.getId());

        partialUpdatedApiaryInformation
            .zoneNumber(UPDATED_ZONE_NUMBER)
            .zoneName(UPDATED_ZONE_NAME)
            .numberHives(UPDATED_NUMBER_HIVES)
            .intensive(UPDATED_INTENSIVE)
            .transhumance(UPDATED_TRANSHUMANCE)
            .coordX(UPDATED_COORD_X)
            .coordY(UPDATED_COORD_Y)
            .coordZ(UPDATED_COORD_Z)
            .numberFrames(UPDATED_NUMBER_FRAMES);

        restApiaryInformationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedApiaryInformation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedApiaryInformation))
            )
            .andExpect(status().isOk());

        // Validate the ApiaryInformation in the database
        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeUpdate);
        ApiaryInformation testApiaryInformation = apiaryInformationList.get(apiaryInformationList.size() - 1);
        assertThat(testApiaryInformation.getZoneNumber()).isEqualTo(UPDATED_ZONE_NUMBER);
        assertThat(testApiaryInformation.getZoneName()).isEqualTo(UPDATED_ZONE_NAME);
        assertThat(testApiaryInformation.getNumberHives()).isEqualTo(UPDATED_NUMBER_HIVES);
        assertThat(testApiaryInformation.getIntensive()).isEqualTo(UPDATED_INTENSIVE);
        assertThat(testApiaryInformation.getTranshumance()).isEqualTo(UPDATED_TRANSHUMANCE);
        assertThat(testApiaryInformation.getCoordX()).isEqualTo(UPDATED_COORD_X);
        assertThat(testApiaryInformation.getCoordY()).isEqualTo(UPDATED_COORD_Y);
        assertThat(testApiaryInformation.getCoordZ()).isEqualTo(UPDATED_COORD_Z);
        assertThat(testApiaryInformation.getNumberFrames()).isEqualTo(UPDATED_NUMBER_FRAMES);
    }

    @Test
    @Transactional
    void patchNonExistingApiaryInformation() throws Exception {
        int databaseSizeBeforeUpdate = apiaryInformationRepository.findAll().size();
        apiaryInformation.setId(count.incrementAndGet());

        // Create the ApiaryInformation
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApiaryInformationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, apiaryInformationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ApiaryInformation in the database
        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchApiaryInformation() throws Exception {
        int databaseSizeBeforeUpdate = apiaryInformationRepository.findAll().size();
        apiaryInformation.setId(count.incrementAndGet());

        // Create the ApiaryInformation
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApiaryInformationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ApiaryInformation in the database
        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamApiaryInformation() throws Exception {
        int databaseSizeBeforeUpdate = apiaryInformationRepository.findAll().size();
        apiaryInformation.setId(count.incrementAndGet());

        // Create the ApiaryInformation
        ApiaryInformationDTO apiaryInformationDTO = apiaryInformationMapper.toDto(apiaryInformation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApiaryInformationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(apiaryInformationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ApiaryInformation in the database
        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteApiaryInformation() throws Exception {
        // Initialize the database
        apiaryInformationRepository.saveAndFlush(apiaryInformation);

        int databaseSizeBeforeDelete = apiaryInformationRepository.findAll().size();

        // Delete the apiaryInformation
        restApiaryInformationMockMvc
            .perform(delete(ENTITY_API_URL_ID, apiaryInformation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ApiaryInformation> apiaryInformationList = apiaryInformationRepository.findAll();
        assertThat(apiaryInformationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
